package Week_03.Course01.gateway02.fiter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author zhangrui
 * @version 1.0
 * @date 2021/1/31
 */
public interface HttpResponseFilter {

    void filter(FullHttpResponse response);
}
