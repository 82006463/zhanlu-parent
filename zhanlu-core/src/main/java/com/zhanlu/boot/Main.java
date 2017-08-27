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
        String result = "success";
        ConfigurableApplicationContext ctx = null;
        try {
            ctx = SpringApplication.run(clazz, args);
        } catch (Exception e) {
            result = "error";
            log.error("Main启动失败", e);
        }
        log.info("service-start-" + result);
        if (ctx != null && result.equals("error")) {
            ctx.close();
            ctx = null;
        }
        if (ctx != null && result.equals("success") && !ctx.containsBean("dispatcherServlet")) {
            try {
                latch.await();
            } catch (Exception e) {
                log.error("Main启动时锁失败", e);
            }
        }
        return ctx;
    }

}
