package cn.zhoufan.caller.server.controller;

import cn.zhoufan.cloud.client.DemoClient;
import cn.zhoufan.common.domian.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhoufan
 * @Date 2021/3/30 16:53
 */
@RestController
@RequestMapping("/caller/server")
public class DemoController {

    @Autowired
    private DemoClient demoClient;
    /**
     * 健康检查接口
     *
     * @return
     */
    @GetMapping("/test")
    public Result health() {
        return Result.success(demoClient.DemoTest());
    }
}
