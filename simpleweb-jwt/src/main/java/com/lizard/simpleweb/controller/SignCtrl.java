package com.lizard.simpleweb.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lizard.simpleweb.model.ApicUser;
import com.lizard.simpleweb.service.UserService;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-15 23:38
 */
@RestController
@RequestMapping("/sign")
public class SignCtrl {
    @Autowired
    UserService userService;

    @RequestMapping("/signin")
    public void signin(HttpServletRequest request, PrintWriter writer) {
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr = " + remoteAddr);
        String remoteHost = request.getRemoteHost();
        System.out.println("remoteHost = " + remoteHost);
        int remotePort = request.getRemotePort();
        System.out.println("remotePort = " + remotePort);
        String sessionId = request.getRequestedSessionId();
        System.out.println("sessionId = " + sessionId);
        String serverName = request.getServerName();
        System.out.println("serverName = " + serverName);
        int serverPort = request.getServerPort();
        System.out.println("serverPort = " + serverPort);
        System.out.println("method = " + request.getMethod());
        System.out.println("requestURI = " + request.getRequestURI());
        writer.write("get request and response OK! ");
        writer.close();

        userService.saveOrUpdate();
    }

    @GetMapping("/user")
    public ApicUser getUserInfo() {
        return new ApicUser("alex", "alex@gmail.com");
    }

    /**
     * 注意RestTemplate发送Post请求URL中的参数应该用@RequestParam接收，而不是直接拼接在Url中， 即@Pathvariable接收不到参数
     */
    @PostMapping(value = "/user/1")
    public String saveOrUpdateUser(@RequestParam String username, HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr = " + remoteAddr);
        System.out.println("requestURI = " + request.getRequestURI());
        System.out.println("username = " + username);
        System.out.println("prod = " + request.getParameter("prod"));
        userService.saveOrUpdate();
        return "success";
    }

    /**
     * 通过application/x-www-form-urlencoded请求体的形式
     */
    @PostMapping(value = "/user1")
    public ApicUser saveAndReturnUser(HttpServletRequest request, ApicUser user) {
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr = " + remoteAddr);
        System.out.println("requestURI = " + request.getRequestURI());
        String age = request.getParameter("age");
        System.out.println("age = " + age);
        String name = request.getParameter("name");
        System.out.println("name = " + name);
        String email = request.getParameter("email");
        System.out.println("email = " + email);
        System.out.println("user = " + user);
        userService.saveOrUpdate();
        return user;
    }

    /**
     * 通过JSON字符串格式的请求体参数
     */
    @PostMapping(value = "/user2")
    public ApicUser saveAndReturnUser(@RequestBody ApicUser user) {
        userService.saveOrUpdate();
        System.out.println("user = " + user);
        return user;
    }

}
