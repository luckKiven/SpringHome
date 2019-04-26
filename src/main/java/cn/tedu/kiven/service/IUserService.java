package cn.tedu.kiven.service;

import cn.tedu.kiven.entity.User;
import cn.tedu.kiven.service.ex.*;

/**
 * 此类为用户业务层接口
 */
public interface IUserService {
    /**
     * 注册用户业务层方法
     */
    void  reg(User user) throws UserConflictException, InsertException;

    /**
     * 用户登录业务层
     */
    User login(String username, String password) throws UserNotFoundException, PasswordErrorException;

    /**
     * 用户修改密码
     * uid 用户登录id
     * password 用户旧密码
     * newPassword 用户新密码
     * @throws UpdateException 修改异常
     * @throws PasswordErrorException  密码错误异常
     * @throws UserNotFoundException 用户未找到异常
     */
    void changePassword(Integer uid ,String oldPassword,String newPassword)throws UpdateException,PasswordErrorException,UserNotFoundException;

    /**
     * 修改资料
     */
    void changeInfo(User user)throws UpdateException,UserNotFoundException;

}
