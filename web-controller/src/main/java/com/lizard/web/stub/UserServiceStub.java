package com.lizard.web.stub;

import com.lizard.web.UserService;
import com.lizard.web.bean.User;

import java.util.List;

/**
 * 远程服务的本地存根
 *
 * 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑。
 *
 * 如提前验证参数，调用失败后伪造容错数据等等，此时就需要在 API 中带上 Stub，客户端生成 Proxy 实例，会把 Proxy 通过构造函数传给 Stub，然后把 Stub 暴露给用户，Stub 可以决定要不要去调 Proxy。
 *
 * @author X
 * @version 1.0
 * @since 2022-10-27 16:49
 **/
public class UserServiceStub implements UserService {

    private UserService userService;

    /**
     * 构造函数将传入真正的远程代理对象
     *
     * @param userService 远程代理对象
     */
    public UserServiceStub(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getList() {
        try {
            return userService.getList();
        } catch (Exception e) {
            // 可以在此处做容错处理，AOP拦截操作等
            e.printStackTrace();
            return null;
        }
    }
}
