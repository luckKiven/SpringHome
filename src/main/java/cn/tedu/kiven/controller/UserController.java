package cn.tedu.kiven.controller;


import cn.tedu.kiven.controller.ex.FileContentTypeException;
import cn.tedu.kiven.controller.ex.FileEmptyException;
import cn.tedu.kiven.controller.ex.FileSizeException;
import cn.tedu.kiven.controller.ex.FileUploadIOException;
import cn.tedu.kiven.entity.User;
import cn.tedu.kiven.service.IUserService;
import cn.tedu.kiven.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 用户控制层,处理成功
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private IUserService service;

    /** 存储上传文件的文件夹名称 */
    public static final String UPLOAD_DIR ="upload";

    /** 允许上传的文件类型的集合*/
    public static final List<String> UPLOAD_CONTENT_TYPES=new ArrayList<String>();

    /** 允许上传文件的最大大小,SpringBoot默认允许2M  */
    public static long UPLOAD_MAX_SIZE = 1*1024*1024;

    /**添加允许上传的文件类型 */
    static{
        UPLOAD_CONTENT_TYPES.add("image/jpeg");
        UPLOAD_CONTENT_TYPES.add("image/png");
        UPLOAD_CONTENT_TYPES.add("image/gif");
        UPLOAD_CONTENT_TYPES.add("image/bmp");
    }


    /**
     * 注册
     * @return
     */
    @PostMapping("/reg")
    public ResponseResult<Void> reg(User user,HttpServletRequest request){
        service.reg(user);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseResult<User> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password, HttpSession session){
        //业务登录并返回一个用户
        User user = service.login(username,password);
        //设置session保存用户登录信息
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",username);
        session.setAttribute("avatar",user.getAvatar());
        session.setAttribute("nickname",user.getNickname());
        return new ResponseResult<User>(SUCCESS,user);
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePwd")
    public ResponseResult<Void> changePassword(@RequestParam("oldPassword") String oldPassword,
                                               @RequestParam("newPassword") String newPassword,HttpSession session){
        Integer uid = getUid(session);
        service.changePassword(uid,oldPassword,newPassword);
        return new ResponseResult<Void>(SUCCESS);
    }

    @PostMapping("/loadAvatar")
    public ResponseResult<String> changeAvatar(@RequestParam("avatar") MultipartFile avatar, HttpServletRequest request,HttpSession session){

        //判断上传的文件为空
        if(avatar.isEmpty()){
            throw new FileEmptyException("请选择文件,文件不能为空");
        }
        //文件的类型不匹配
        if(!UPLOAD_CONTENT_TYPES.contains(avatar.getContentType())){
            throw new FileContentTypeException("请重新选择文件,文件类型不匹配");
        }
        //上传的文件最大大小
        if(avatar.getSize()>UPLOAD_MAX_SIZE){
            throw new FileSizeException("请重新选择文件,最大支持:"+UPLOAD_MAX_SIZE+"M文件!");
        }

        //确定上传的文件夹
        String parentPath = request.getServletContext().getRealPath(UPLOAD_DIR);
        File parent = new File(parentPath);
        if(!parent.exists()){
            parent.mkdirs();
        }

        //确定上传的文件名
        String OriginalFileName = avatar.getOriginalFilename();
        String suffix = "";
        int beginIndex= OriginalFileName.lastIndexOf(".");
        if(beginIndex!=-1){
            suffix=OriginalFileName.substring(beginIndex);
        }
        String fileName = UUID.randomUUID() + suffix;

        //存储到服务器该文件夹
        File dest = new File(parent,fileName);
        try {
            avatar.transferTo(dest);
        }catch (IOException e){
            throw new FileUploadIOException("系统繁忙,暂不支持上传头像功能");
        }

        //执行存储头像路径到session中
        session.setAttribute("avatar","/"+UPLOAD_DIR+"/"+fileName);
        ResponseResult<String> rr = new ResponseResult<String>(SUCCESS,"/"+UPLOAD_DIR+"/"+fileName);
        return rr;
    }

    /**
     * 修改资料
     */
    @PostMapping("/updateInfo")
    public ResponseResult<String> changeInfo(User user,HttpSession session){
        Integer uid = getUid(session);
        String avatar ="";
        if(getAvatar(session)!=null){
            avatar = getAvatar(session);
            user.setAvatar(avatar);
        }
        user.setUid(uid);
        service.changeInfo(user);
        ResponseResult<String> rr = new ResponseResult<String>(SUCCESS,avatar);
        return rr;
    }

}
