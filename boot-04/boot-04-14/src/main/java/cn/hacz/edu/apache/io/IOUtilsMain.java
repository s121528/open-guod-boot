package cn.hacz.edu.apache.io;

import org.apache.commons.io.IOUtils;
import org.springframework.ui.Model;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 文件操作工具类：IOUtils读取一个网页的源代码的代码
 *
 * @author guod
 * @version 1.0
 * @date 日期:2018/7/13 时间:8:58
 * @since 1.8
 */
public class IOUtilsMain {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = new URL("http://www.baidu.com").openStream();
        // 01 toString读取链接地址网页
        String s = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
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
        List<String> lines = IOUtils.readLines(new InputStreamReader(new FileInputStream(new File("")), StandardCharsets.UTF_8));

    }
}