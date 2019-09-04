package cn.hacz.edu.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * project - GitHub整理
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/2/26 时间:9:44
 * @JDK 1.8
 * @Description 功能模块：
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "car")
public class CarProperties {
    // 价格
    private int price;
    // 重量
    private int weight;
}