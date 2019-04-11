package cn.hacz.edu;

import cn.hacz.edu.config.SampleRedisConfig;
import cn.hacz.edu.entity.User;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/11 时间:13:14
 * @JDK 1.8
 * @Description 功能模块：
 */
@SpringBootApplication
@RestController
@EnableApolloConfig
public class ApolloClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApolloClientApplication.class, args);
    }

    @Autowired
    private SampleRedisConfig sampleRedisConfig;

    @ApolloConfig
    private Config config;

    @ApolloJsonValue("${jsonBeanProperty:[]}")
    private List<User> anotherJsonBeans;


    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("timeout")) {
            System.out.println(config.getIntProperty("timeout", 0));
        }
    }

    @GetMapping(value = "/index01")
    public String index01() {
        return "hello apollo";
    }

    @GetMapping("/index02")
    public String index02() {
        return sampleRedisConfig.getCommandTimeout() + "--" + sampleRedisConfig.getExpireSeconds();
    }


    @GetMapping("/index03")
    public String index03() {
        Set<String> propertyNames = config.getPropertyNames();
        propertyNames.forEach(key -> System.err.println(key + "=" + config.getIntProperty(key, 0)));
        return propertyNames.toString();
    }

    @GetMapping("/index04")
    public void index04() {
        anotherJsonBeans.forEach(item -> System.err.println(item.getUsername() + "--" + item.getPassword()));
    }
}
