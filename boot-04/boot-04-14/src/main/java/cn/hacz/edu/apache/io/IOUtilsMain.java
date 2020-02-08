package cn.hacz.edu.apache.io;

import org.apache.commons.io.IOUtils;
import org.springframework.ui.Model;

import javax.xml.bind.DatatypeConverter;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * project - GitHub整理
 *
 * @author guod
 * @version 1.0
 * @date 日期:2018/7/13 时间:8:58
 * @JDK 1.8
 * @Description 功能模块：文件操作工具类：IOUtils读取一个网页的源代码的代码
 */
public class IOUtilsMain {
    public static void main(String[] args) throws Exception {
        String encoding = "UTF-8";
        InputStream inputStream = new URL("http://www.baidu.com").openStream();
        // 01 toString读取链接地址网页
        String s = IOUtils.toString(inputStream, encoding);
        System.out.println(s);
        // 02 toInputStream
        byte[] bytes = new byte[5];
        InputStream is = IOUtils.toInputStream("hello world 开发测试", StandardCharsets.UTF_8);
        // 03 read
        IOUtils.read(is, bytes);
        System.out.println(Arrays.toString(bytes));
        System.out.println(DatatypeConverter.printHexBinary(bytes));
        System.out.println(new String(bytes));
        byte[] newBytes = new byte[10];
        // 04 copy
    }
}