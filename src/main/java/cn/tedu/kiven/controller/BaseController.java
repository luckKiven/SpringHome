package cn.tedu.kiven.controller;

import cn.tedu.kiven.controller.ex.FileContentTypeException;
import cn.tedu.kiven.controller.ex.FileEmptyException;
import cn.tedu.kiven.controller.ex.FileSizeException;
import cn.tedu.kiven.controller.ex.FileUploadIOException;
import cn.tedu.kiven.service.ex.*;
import cn.tedu.kiven.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制器的基类,其中封装统一捕获处理异常,和从中获取userid,username的方法.
 */
public abstract class BaseController {
    /**
     * 成功的常量,经常用到,所以封装为常量
     */
    public static final Integer  SUCCESS = 200;

    /**
     * 统一处理所有异常,并以json形式返回给客户端.
     * @param e
     * @return 自定义的类型,里面包含了状态码, 消息,即对象
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult<Void> HandlerException(Exception e){
        ResponseResult<Void> rr = new ResponseResult<Void>();
        rr.setMessage(e.getMessage());
        if(e instanceof UserConflictException){
            //用户名重复异常
            rr.setState(405);
        }else if(e instanceof InsertException){
            //服务器插入异常
            rr.setState(500);
        }else if(e instanceof UserNotFoundException){
            //用户名不存在
            rr.setState(406);
        }else if(e instanceof PasswordErrorException){
            //密码错误
            rr.setState(407);
        }else if(e instanceof UpdateException){
            //修改异常
            rr.setState(501);
        }else if(e instanceof FileContentTypeException){
            //文件类型不匹配异常
            rr.setState(600);
        }else if(e instanceof FileEmptyException){
            //文件为空异常
            rr.setState(601);
        }else if(e instanceof FileUploadIOException){
            //文件上传失败异常
            rr.setState(602);
        }else if(e instanceof FileSizeException){
            //文件大小超限至
            rr.setState(603);
        }
        return rr;
    }
    /**
     * 从session中获取已经登陆的头像
     * @param session
     * @return
     */
    public String getAvatar(HttpSession session){
        String avatar = session.getAttribute("avatar").toString();
        return avatar;
    }
    /**
     * 从session中获取已经登陆的昵称
     * @param session
     * @return
     */
    public String getNickName(HttpSession session){
        String nickname = session.getAttribute("nickname").toString();
        return nickname;
    }

    /**
     * 从session中获取已经登陆的用户名
     * @param session
     * @return
     */
    public String getUsername(HttpSession session){
        String username = session.getAttribute("username").toString();
        return username;
    }

    /**
     * 从session中获取已经登录的uid
     * @param session
     * @return
     */
    public Integer getUid(HttpSession session){
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        return uid;
    }



}
