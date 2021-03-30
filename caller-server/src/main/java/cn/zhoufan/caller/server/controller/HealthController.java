package cn.zhoufan.caller.server.controller;

import cn.zhoufan.common.domian.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 健康检查
 * @Author : wangyongjiu
 * @Date : 2020/12/31
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    /**
     * 健康检查接口
     *
     * @return
     */
    @GetMapping
    public Result<?> health() {
        return Result.success();
    }
}
