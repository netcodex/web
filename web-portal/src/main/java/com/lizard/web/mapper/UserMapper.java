package com.lizard.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lizard.web.bean.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-02 22:52
 */
@Mapper
public interface UserMapper {

    /**
     * 根据ID查询单个用户信息
     *
     * @param id
     *            用户id
     * @return 用户基本信息
     */
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user (name,age,email,gender) values(#{name},#{age},#{email},#{gender})")
    int insert(User user);

    @Select("select * from user")
    List<User> getUserList();

    @Select("select * from user")
    Set<User> getUserSet();

    @Select("select name,age from user")
    Map<String, Integer> getNameAgeMap();

    @Select("select age from user where id = #{id}")
    Integer getAge(@Param("id") Integer id);

    @Select("select name from user where id = #{id}")
    String getName(@Param("id") Integer id);
}
