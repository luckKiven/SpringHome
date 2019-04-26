package cn.tedu.kiven.service.impl;

import cn.tedu.kiven.entity.User;
import cn.tedu.kiven.mapper.UserMapper;
import cn.tedu.kiven.service.IUserService;
import cn.tedu.kiven.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * 此类为用户业务层实体类
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper mapper;
    /**
     * 用户注册业务方法
     * @param user 控制器传过来的用户参数
     * @throws UserConflictException 用户名重复异常
     * @throws InsertException 用户插入异常
     */
    @Override
    public void reg(User user) throws UserConflictException, InsertException {
        //所需要用到的参数
        String username = user.getUsername();
        String password = user.getPassword();
        Date now = new Date();

        //判断该用户是否注册,如果注册则抛用户名重复异常
        User data = findByUsername(username);
        if(data!=null){
            throw new  InsertException("注册失败,用户名重复异常");
        }

        //获取盐,并且密码执行MD5加密
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(password,salt);

        //封装is_delete,盐,以及创建时间,人,修改时间,人,执行插入操作
        user.setIsDelete(0);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setCreatedTime(now);
        user.setCreatedUser(username);
        user.setModifiedTime(now);
        user.setModifiedUser(username);
        System.out.println(user.getAvatar());
        addNew(user);
    }
    /**
     * 用户登录业务
     */
    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordErrorException {
        //通过该用户名查到该用户的信息是否为null
        User user = findByUsername(username);
        if(user==null){
            throw new UserNotFoundException("登录失败! 用户名或密码错误");
        }
        //需要用到的参数
        String oldMd5Password = user.getPassword();
        String salt = user.getSalt();
        Integer isDelete = user.getIsDelete();



        //查看该用户数据是否已被删除is_delete
        if("1".equals(isDelete)){
            throw new UserNotFoundException("登录失败! 用户名或密码错误");
        }

        //判断密码是否正确,通过盐加密参数密码,equls判断相等
        String newMd5Password = getMd5Password(password,salt);
        if(!newMd5Password.equals(oldMd5Password)){
            throw new PasswordErrorException("登陆失败,用户名或密码错误");
        }

        user.setSalt(null);
        user.setPassword(null);
        return user;
    }
    /**
     * 修改密码
     * uid 用户登录id
     * password 用户旧密码
     * newPassword 用户新密码
     */
    public void changePassword(Integer uid ,String oldPassword,String newPassword){

        //根据用户当前登录的uid,查找当前用户信息
        User data =  findByUid(uid);

        if(data==null){
            throw new UserNotFoundException("修改失败,用户名不存在!");
        }

        //判断是否已经被删除
        if(data.getIsDelete()==1){
            throw new UserNotFoundException("修改失败,用户名不存在!");
        }

        //取出盐,旧密码,判断旧密码,加密是否相等
        String salt = data.getSalt();
        String password = data.getPassword();
        String oldMd5Password = getMd5Password(oldPassword,salt);
        if(!password.equals(oldMd5Password)){
            throw new PasswordErrorException("修改失败,密码错误");
        }

        //执行修改
        String newMd5Password = getMd5Password(newPassword,salt);
        updatePassword(uid,newMd5Password,data.getUsername(),new Date());

    }

    /**
     * 修改资料
     * @param user
     * @throws UpdateException
     * @throws UserNotFoundException
     */
    public void changeInfo(User user)throws UpdateException,UserNotFoundException{
        //判断该用户是否存在
        User data = findByUid(user.getUid());
        if(data==null){
            throw new UserNotFoundException("修改失败! 该用户不存在");
        }

        //判断用户是否被删除
        if(data.getIsDelete()==1){
            throw new UserNotFoundException("修改失败! 该用户不存在");
        }

        //执行修改操作,封装修改人,修改时间
        user.setModifiedUser(data.getUsername());
        user.setModifiedTime(new Date());
        updateInfo(user);
    }

    /**
     * 添加用户
     */
    private void addNew(User user){
        Integer rows = mapper.addNew(user);
        if(rows!=1){
            throw new UserConflictException("注册失败,您是试图注册的用户名"+user.getUsername()+"已存在");
        }
    }
    /**
     * 修改资料
     */
    private void updateInfo(User user){
        Integer rows = mapper.updateInfo(user);
        if(rows!=1){
            throw new UpdateException("修改失败!系统繁忙请稍后重试!");
        }
    }
    /**
     * 修改密码
     */
    private void updatePassword(Integer uid,String password, String modifiedUser, Date modifiedTime ){
        Integer rows = mapper.updatePassword(uid,password,modifiedUser,modifiedTime);
        if(rows!=1) {
            throw new UpdateException("修改失败!系统繁忙,请稍后重试");
        }
    }
    /**
     * 根据username去查找用户
     */
    private User findByUsername(String username){
        return mapper.findByUsername(username);
    }
    /**
     * 根据uid查询用户信息
     */
    private User findByUid(Integer uid){
        return mapper.findByUid(uid);
    }
    /**
     * 执行密码加密
     * @param password 密码原文
     * @param salt	       盐值
     * @return		       加密后的密文//密钥yue
     */
    private String getMd5Password(String password , String salt) {
        /**
         * 加密规则
         * 1.将盐值添加到密码原文左侧
         * 2.执行加密10次
         * */
        String str = salt + password;
        for (int i = 0; i < 10; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
        }
        return str;
    }
}
