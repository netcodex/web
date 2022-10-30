package com.lizard.web.controller;

import com.lizard.web.UserService;
import com.lizard.web.bean.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author X
 * @version 1.0
 * @since 2022-10-26 19:34
 **/
@Controller
public class PortalUserController {
    /**
     * check 启动时检查服务是否存在，默认为true timeout 服务连接超时 version 多版本时指定服务版本
     * stub 设置本地存根
     * mock 设置服务降级策略
     *      1） 设置为true，表示引用UserService同目录下的mock实现类，mock实现类同样实现UserService接口
     *      2） 设置为mock实现类的引用
     *      3） [fail|force]:return|throw xxx force是直接不调用强制执行mock方法，默认为fail，xxx为指定的返回值或者要抛出的异常
     */
    @DubboReference(check = false, version = "2.0", retries = 2, stub = "com.lizard.web.stub.UserServiceStub",
        mock = "fail:throw", methods = {@Method(name = "getList", timeout = 1000)})
    private UserService userService;

    @GetMapping("getUsers")
    @ResponseBody
    public List<User> getPortalUserList() {
        return userService.getList();
    }
}
