package cn.tedu.kiven.service;


import cn.tedu.kiven.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
    @Autowired
    private IUserService service;

    @Test
    public void reg(){
        User user = new User();
        user.setUsername("jixiang88");
        user.setPassword("123456");
        user.setNickname("Kiven1");
        service.reg(user);
        System.out.println("OK");
    }
    @Test
    public void login(){
        String username = "jixiang";
        String password = "123456";
        User user=service.login(username,password);
        System.out.println(user);
    }
    @Test
    public void changPassword(){
        Integer uid =1 ;
        String oldPassword ="123456";
        String newPassword = "123";
        service.changePassword(uid,oldPassword,newPassword);
        System.out.println("OK");
    }
    @Test
    public void changInfo(){
        Integer uid =1 ;
        User user = new User();
        user.setUid(uid);
        user.setNickname("12456");
        user.setRealname("朱柯浦");
        user.setGender(0);
        user.setPhone("13420019471");
        service.changeInfo(user);
        System.err.println("OK");
    }
}
