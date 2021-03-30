package cn.zhoufan.cloud.client.fallback;

import cn.zhoufan.cloud.client.DemoClient;
import cn.zhoufan.common.domian.Result;
import cn.zhoufan.common.enums.ResultCode;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author zhoufan
 * @Date 2021/3/30 16:02
 */
@Slf4j
@Component
public class DemoClientFallback implements FallbackFactory<DemoClient> {
    @Override
    public DemoClient create(Throwable throwable) {
        return new DemoClient() {
            @Override
            public Result DemoTest() {
                log.error("调用 DemoClient.DemoTest 失败，message:[{}]",throwable.getMessage());
                return Result.error(ResultCode.exception);
            }
        };
    }
}
