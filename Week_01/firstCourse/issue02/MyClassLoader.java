import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

/**
 * 
 * @author zhangrui
 * @version 1.0
 * @date 2021/1/9 12:57
 */

public class MyClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            //1.通过自定义的类加载器将获取到的类对象加载
            Class<?> clazz = new MyClassLoader().findClass("Hello");
            //2.通过类对象创建实例对象
            Object obj = clazz.newInstance();
            //3.通过实例对象调用方法
            Method method = clazz.getDeclaredMethod("hello");
            method.invoke(obj);
        } catch (InstantiationException | IllegalAccessException |NoSuchMethodException |
                InvocationTargetException| ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //1.寻找需要加载文件的路径(.xlass文件与本类编译的类文件位置相同)
        String path = MyClassLoader.class.getResource("").getPath() + name + ".xlass";
        File file = new File(path);
        //File file1 = new File("E:\\JavaCourse\\2021_01_06\\Hello\\"+name + ".class");
        byte[] bytes = null;
        int len = 0;
        //2.读取所提供的字节码，将其转换为对象并获取class对象
        try {
            bytes = Files.readAllBytes(file.toPath());
            for (int i = 0; i < (len = bytes.length); i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(name, bytes, 0, len);
    }
}
