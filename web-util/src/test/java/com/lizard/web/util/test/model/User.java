package com.lizard.web.util.test.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 描述：用户数据模型
 *
 * @author x
 * @since 2020-08-01 11:57
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private List<Card> cards;
    private Date birthDay;
    private User wife;
}
