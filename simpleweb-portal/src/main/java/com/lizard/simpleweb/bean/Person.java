package com.lizard.simpleweb.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author x Date: 2020-05-21 16:21
 */
@Component
// ConfigurationProperties还支持ignoreInvalidFields，ignoreUnknownFields忽略非法参数设置
@ConfigurationProperties(prefix = "person")
public class Person {

    @Value("${person.name}")
    private String name;
    private int age;
    private boolean adult;
    private Date birth;
    private Map<String, String> account;
    private List<String> hobbies;
    private User wife;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, String> getAccount() {
        return account;
    }

    public void setAccount(Map<String, String> account) {
        this.account = account;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public User getWife() {
        return wife;
    }

    public void setWife(User wife) {
        this.wife = wife;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + ", adult=" + adult + ", birth=" + birth
            + ", account=" + account + ", hobbies=" + hobbies + ", wife=" + wife + '}';
    }
}
