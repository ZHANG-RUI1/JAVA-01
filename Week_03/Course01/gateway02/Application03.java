package Week_03.Course01.gateway02;

/**
 * @author zhangrui
 * @version 1.0
 * @date 2021/1/30
 */
public class Application03 {
    public static void main(String[] args) throws InterruptedException {
        NettyHttpServer03 httpServer = new NettyHttpServer03(8089);
        httpServer.start();
    }

}
