package cn.tedu.kiven.config;

import cn.tedu.kiven.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册拦截器类
 * @author tedu-uid
 *
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //实例之前创建的拦截器
        LoginInterceptor in= new LoginInterceptor();
        List<String> list = new ArrayList<String>();
        list.add("/sg/css/**");
        list.add("/sg/fonts/**");
        list.add("/sg/images/**");
        list.add("/sg/js/**");
        list.add("/users/login");
        list.add("/users/reg");
        list.add("/sg/index.html");
        list.add("/sg/Login.html");
        list.add("/sg/registered.html");
        list.add("/");
        list.add("/index");
        registry.addInterceptor(in).addPathPatterns("/**").excludePathPatterns(list);
    }
}
