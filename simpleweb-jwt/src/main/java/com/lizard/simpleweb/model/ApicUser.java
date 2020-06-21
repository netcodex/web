package com.lizard.simpleweb.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-21 0:28
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ApicUser {
    private Integer age;
    @NonNull
    private String name;
    @NonNull
    private String email;
    private String gender;
    private Date birth;
}
