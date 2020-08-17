package cn.hacz.edu;

import cn.hacz.edu.repository.base.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * project - GitHub整理 中原金控后台管理服务
 *
 * @author guod
 * @version 1.0
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class Boot0806Application {
    public static void main(String[] args) {
        SpringApplication.run(Boot0806Application.class, args);
    }
}
