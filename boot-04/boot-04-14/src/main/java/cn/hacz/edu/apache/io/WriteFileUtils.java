package cn.hacz.edu.apache.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件、流的操作
 *
 * @author guod
 * @version 1.0
 */
public class WriteFileUtils {
    public static void main(String[] args) throws Exception {
        // 字符集
        Charset c = StandardCharsets.UTF_8;
        // 定义一个文件
        File f = new File("D:/01.txt");
        String s = "你好,世界\n hello world";
        // 把内容写进文件,用UTF-8编码
        FileUtils.write(f, s, c);
        // 把内容追加进文件
        FileUtils.write(f, s, c, true);

        // 把一个List写进文件，List的每一个元素是一行
        List<String> lines = Stream.of("你好,世界", "hello, world").collect(Collectors.toList());
        FileUtils.writeLines(f, lines);
        FileUtils.writeLines(f, lines, true);
        FileUtils.writeLines(f, "UTF-8", lines, true);
        // 写入二进制数据
        FileUtils.writeByteArrayToFile(f, s.getBytes());
        // writeStringToFile
        FileUtils.writeStringToFile(f, s, c);
    }
}
