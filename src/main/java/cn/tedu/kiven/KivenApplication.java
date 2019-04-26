package cn.tedu.kiven;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@MapperScan("cn.tedu.kiven.mapper")
@SpringBootApplication
public class KivenApplication {

    @Bean
    public MultipartConfigElement multipartConfiguration() {
        MultipartConfigFactory mcf =new MultipartConfigFactory();
        //一下实例可以放在5*1024*1024位置当参数,建议用此方法
        //DataSize maxSize = DataSize.ofMegabytes(100l);
        mcf .setMaxRequestSize(5*1024*1024);
        mcf .setMaxFileSize(5*1024*1024);
        return mcf.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(KivenApplication.class, args);
    }

}
