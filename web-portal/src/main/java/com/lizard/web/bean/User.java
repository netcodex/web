package com.lizard.web.bean;

import java.util.Date;

import lombok.Data;

/**
 * @author x Date: 2020-05-21 16:14
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String gender;
    private Date birth;

}
