package cn.tedu.kiven.mapper;

import cn.tedu.kiven.entity.User;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Date;

/**
 * 用户管理的接口
 */

public interface UserMapper {
    /**
     * 注册用户
     */
    Integer addNew(User user);

    /**
     * 根据username去查找用户
     */
    User findByUsername(String username);

    /**
     * 根据uid查询用户信息
     */
    User findByUid(Integer uid);

    /**
     * 修改密码
     */
    Integer updatePassword(@Param("uid") Integer uid,
                           @Param("password") String password,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime );
    /**
     * 修改资料
     */
    Integer updateInfo(User user);

}
