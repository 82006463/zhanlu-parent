package com.zhanlu.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/8/27.
 */
@Slf4j
@SpringBootApplication
public class Main {

    private static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        Main.run(Main.class, args);
    }

    public static ConfigurableApplicationContext run(Class<?> clazz, String[] args) {
        ConfigurableApplicationContext ctx = null;
        try {
            ctx = SpringApplication.run(Main.class, args);
            if (!ctx.containsBean("dispatcherServlet")) {
                latch.await();
            }
        } catch (Exception e) {
            log.error("Main启动失败", e);
            if (ctx != null) {
                ctx.close();
            }
        }
        return ctx;
    }

}
