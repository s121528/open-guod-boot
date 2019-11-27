package cn.hacz.edu.readproperties;

import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.setting.Setting;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

/**
 * project - GitHub整理
 *
 * @author guod
 * @version 1.0
 * @date 日期:2018/10/17 时间:14:15
 * @JDK 1.8
 * @Description 功能模块：读取配置文件信息
 */
public class MainTest {
    public static void main(String[] args) throws IOException, ConfigurationException {
        // 方式01
        Configuration configuration = new PropertiesConfiguration("conf/pay.properties");
        String string = configuration.getString("pay.name");
        System.out.println(string);

        // 方式02
        Properties properties = PropertiesLoaderUtils.loadAllProperties("conf/pay.properties");
        String property = properties.getProperty("pay.name");
        System.out.println(property);

        // 方式03
        Setting set = new Setting("conf/file.properties");
        System.out.println(set.get("name"));

        // spring的工具类
        File file = ResourceUtils.getFile("classpath:conf/pay.properties");
        List<String> list = FileUtils.readLines(file, StandardCharsets.UTF_8);
        for (String s : list) {
            System.out.println(s);
        }
    }
}