package cn.tedu.kiven.mapper;


import cn.tedu.kiven.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
    @Autowired
    private UserMapper mapper;
    @Test
    public void addnew(){
        User user =new User();
        user.setUsername("jixiang3");
        user.setPassword("123456");
        user.setNickname("Kiven");
        Integer rows = mapper.addNew(user);
        System.out.println(rows);
    }
    @Test
    public void findByUsername(){
        String username = "jixiang";
        User byUsername = mapper.findByUsername(username);
        System.out.println(byUsername);
    }
    @Test
    public void findByUid(){
        Integer uid = 1;
        User  user = mapper.findByUid(uid);
        System.out.println(user);
    }
    @Test
    public void updatePassword(){
        Integer uid = 1;
        Date now = new Date();
        String username = "超级管理员";
        String password = "77777";
        Integer rows = mapper.updatePassword(uid,password,username,now);
        System.out.println(rows);
    }
    @Test
    public void updateInfo(){
        Integer uid =1 ;
        Date now = new Date();
        User user = new User();
        user.setUid(uid);
        user.setNickname("狗子");
        user.setRealname("朱柯浦");
        user.setGender(0);
        user.setPhone("13420019471");
        user.setModifiedUser("超级管理员");
        user.setModifiedTime(now);
        Integer rows=mapper.updateInfo(user);
        System.out.println(rows);
    }
}
