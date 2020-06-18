package com.lizard.simpleweb.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
