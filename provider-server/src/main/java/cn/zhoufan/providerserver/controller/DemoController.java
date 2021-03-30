package cn.zhoufan.providerserver.controller;

import cn.hutool.core.collection.CollUtil;
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
@RequestMapping("/provider/server")
public class DemoController {

    /**
     * 健康检查接口
     *
     * @return
     */
    @GetMapping("demoTest")
    public Result demoTest() {
        int a = 1 / 0;
        return Result.success(CollUtil.newArrayList("周帆"));
    }
}
