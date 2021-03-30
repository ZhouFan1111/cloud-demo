package cn.zhoufan.cloud.client;

import cn.zhoufan.cloud.client.fallback.DemoClientFallback;
import cn.zhoufan.common.domian.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhoufan
 * @Date 2021/3/30 16:05
 */
@FeignClient(value = "provider-server", fallbackFactory = DemoClientFallback.class)
public interface DemoClient {

    @RequestMapping("/provider/server/demoTest")
    Result DemoTest();

}
