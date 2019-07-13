package cn.hacz.edu.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/3 时间:8:58
 * @JDK 1.8
 * @Description 功能模块：
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "gf", type = "book")
public class Book {
    @Id
    private Integer id;
    private String bookName;
    private String author;
}
