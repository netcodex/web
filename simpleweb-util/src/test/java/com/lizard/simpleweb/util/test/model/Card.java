package com.lizard.simpleweb.util.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：银行卡数据模型
 *
 * @author x
 * @since 2020-08-04 22:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Integer serial;
    private String bankName;
    private String password;
}