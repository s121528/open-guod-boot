package cn.hacz.edu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/11 时间:13:27
 * @JDK 1.8
 * @Description 功能模块：
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis.cache")
public class SampleRedisConfig {
    private int expireSeconds;
    private int commandTimeout;
}
