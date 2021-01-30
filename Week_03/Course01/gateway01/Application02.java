package Week_03.Course01.gateway01;

/**
 * @author zhangrui
 * @version 1.0
 * @date 2021/1/30
 */
public class Application02 {
    public static void main(String[] args) throws InterruptedException {
        NettyHttpServer02 httpServer = new NettyHttpServer02(8089);
        httpServer.start();
    }

}
