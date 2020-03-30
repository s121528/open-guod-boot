package cn.hacz.edu.readproperties;

import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.setting.Setting;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * 读取配置文件信息
 *
 * @author guod
 * @version 1.0
 * @date 日期:2018/10/17 时间:14:15
 * @since 1.8
 */
public class MainTest {
    @Test
    public void test() throws IOException {
        // 方式01，模块下（项目下）
        Properties ps01 = new Properties();
        ps01.load(new FileInputStream(new File("pom.xml")));
    }

    public static void main(String[] args) throws Exception {
        // 方式02，resources下
        Properties ps02 = new Properties();
        String path = Objects.requireNonNull(MainTest.class.getClassLoader().getResource("application.properties")).getPath();
        InputStream rs = MainTest.class.getClassLoader().getResourceAsStream("application.properties");
        ps02.load(rs);
        String user = ps02.getProperty("guodd.name");
        System.out.println(user);

        // 方式03，resources下
        Configuration configuration = new PropertiesConfiguration("conf/pay.properties");
        String string = configuration.getString("pay.name");
        System.out.println(string);

        // 方式04，resources下
        Properties properties = PropertiesLoaderUtils.loadAllProperties("conf/pay.properties");
        String property = properties.getProperty("pay.name");
        System.out.println(property);

        // 方式05，resources下
        Setting set = new Setting("conf/file.properties");
        System.out.println(set.get("name"));

        // 方式06：spring的工具类
        File file = ResourceUtils.getFile("classpath:conf/pay.properties");
        List<String> list = FileUtils.readLines(file, StandardCharsets.UTF_8);
        for (String s : list) {
            System.out.println(s);
        }

        // 方式07：
        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        InputStream inputStream = classPathResource.getInputStream();
        List<String> strings = IOUtils.readLines(inputStream, StandardCharsets.UTF_8);
        System.out.println(strings);
    }
}