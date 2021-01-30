package Week_03.Course01.gateway01;

import Week_02.Course_02.netty.HttpInitializer;
import com.sun.webkit.EventLoop;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author zhangrui
 * @version 1.0
 * @date 2021/1/29
 */
public class NettyHttpServer02 {
    private  int port;

    public NettyHttpServer02() {
    }

    public NettyHttpServer02(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            // 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
            b.option(ChannelOption.SO_BACKLOG, 128)
                    // TCP_NODELAY就是用于启用或关于Nagle算法。
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 是否启用心跳保活机制。TCP套接字建立连接后,并且在两个小时左右上层没有任何数据传输的情况下，机制才会被激活
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    // SO_REUSEADDR允许启动一个监听服务器并捆绑其众所周知端口
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInitializer());

            Channel ch = b.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为：http://127.0.0.1:" +  port  + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
