package Week_03.Course01.gateway02.fiter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author zhangrui
 * @version 1.0
 * @date 2021/1/31
 */
public interface HttpRequestFilter {

    void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);
}
