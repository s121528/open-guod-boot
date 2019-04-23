package cn.hacz.edu.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * project -
 *
 * @author yanfa07
 * @version 1.0
 * @date 日期:2019/4/23 时间:16:09
 * @JDK 1.8
 * @Description 功能模块：
 */
@Component
@Order(1)
public class Runner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("我应该是初始化资源第一名");
    }
}
