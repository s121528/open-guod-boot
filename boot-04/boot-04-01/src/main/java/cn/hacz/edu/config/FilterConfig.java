package cn.hacz.edu.config;

import cn.hacz.edu.filter.OtherTimeFilter;
import cn.hacz.edu.interceptor.TimeInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dong
 * @version 3.0
 */
@Configuration
public class FilterConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean otherTimeFilert() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        OtherTimeFilter otherTimeFilter = new OtherTimeFilter();
        registrationBean.setFilter(otherTimeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TimeInterceptor timeInterceptor = new TimeInterceptor();
        registry.addInterceptor(timeInterceptor).addPathPatterns("/test/**");
        registry.addInterceptor(timeInterceptor);
    }
}
