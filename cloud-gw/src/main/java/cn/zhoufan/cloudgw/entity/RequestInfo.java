package cn.zhoufan.cloudgw.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * @Author zhoufan
 * @Date 2021/3/2 19:49
 */
@Data
public class RequestInfo {

    private String requestUrl;
    private HttpHeaders headers;
    private String params;
    private String requestBody;
    private String address;
    private String method;

    @Override
    public String toString() {
        if (StrUtil.equals(method, HttpMethod.GET.name())) {
            return "RequestInfo{" +
                    "requestUrl=[" + requestUrl + ']' +
                    ", headers=[" + headers + ']' +
                    ", params=[" + params + ']' +
                    ", address=[" + address + ']' +
                    ", method=[" + method + ']' +
                    '}';
        }
        return "RequestInfo{" +
                "requestUrl=[" + requestUrl + ']' +
                ", headers=[" + headers + ']' +
                ", params=[" + params + ']' +
                ", requestBody=[" + requestBody + ']' +
                ", address=[" + address + ']' +
                ", method=[" + method + ']' +
                '}';
    }
}
