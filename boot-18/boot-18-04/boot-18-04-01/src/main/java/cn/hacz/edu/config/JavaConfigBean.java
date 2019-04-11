package cn.hacz.edu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/11 时间:13:25
 * @JDK 1.8
 * @Description 功能模块：
 */
@Configuration
public class JavaConfigBean {
    @Value("${timeout:20}")
    private int timeout;

    public int getTimeout() {
        return timeout;
    }
}
