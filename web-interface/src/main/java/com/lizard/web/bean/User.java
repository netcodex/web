package com.lizard.web.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author x Date: 2020-05-21 16:14
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 4490281747671674859L;

    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String gender;
    private Date birth;
}
