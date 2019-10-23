package com.course;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PreDestroy;

//手写 application 类
@EnableScheduling
@EnableSwagger2
@SpringBootApplication
public class Application {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context = SpringApplication.run(Application.class);
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }

}
