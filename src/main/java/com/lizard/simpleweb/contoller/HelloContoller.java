package com.lizard.simpleweb.contoller;

import com.lizard.simpleweb.bean.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

/**
 * @author x
 * Date: 2020-06-06 16:29
 */
@RestController
public class HelloContoller {
    @GetMapping("/hello")
    public void hello() {
        System.out.println("helo!");
    }

    @GetMapping("/hello/{userid}")
    public void getUserId(@PathVariable("userid") String userid) {
        System.out.println("getHello!" + userid);
    }

    /**
     * 直接从Session中读取指定属性
     */
    @GetMapping("/hello/getSession")
    public void testGetSessionAttribute(@SessionAttribute("user") User user) {
        System.out.println("user = " + user);
    }

    /**
     * 设置session属性
     */
    @GetMapping("/hello/setAttr")
    public void setSessionAttr(HttpSession session) {
        User user = new User();
        user.setName("lina");
        user.setEmail("zak@gmail.com");
        session.setAttribute("user", user);
    }

    /**
     * 绑定属性
     */
    @ModelAttribute
    public void bindAttr(Model model) {
        User user = new User();
        user.setName("jaky");
        user.setEmail("jaky@gmail.com");
        model.addAttribute("userb", user);
    }

    /**
     * 直接通过ModelAttribute获取绑定的属性
     */
    @GetMapping("/hello/getModelAttr")
    public void getModelAttribute(@ModelAttribute("userb") User user) {
        System.out.println("user = " + user);
    }

}
