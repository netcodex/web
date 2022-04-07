package com.lizard.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lizard.web.bean.User;

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
     * @param id 用户id
     * @return 用户基本信息
     */
    @Select("select * from apic_user where id = #{id}")
    User getUserById(@Param("id") Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into apic_user (name,age,email,gender) values(#{name},#{age},#{email},#{gender})")
    int insert(User user);
}
