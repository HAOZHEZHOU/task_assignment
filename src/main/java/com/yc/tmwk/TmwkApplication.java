package com.yc.tmwk;

import com.yc.tmwk.controller.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.yc.tmwk.dao")
@ComponentScan(basePackages = {"com.yc.tmwk.service","com.yc.tmwk.controller","com.yc.tmwk.config" ,"com.yc.tmwk.util"})
@SpringBootApplication
public class TmwkApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(TmwkApplication.class, args);
        WebSocketServer.setCtx(ctx);
    }
}
