package cn.hacz.edu.entity;

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
@Document(indexName = "gf", type = "book")
public class Book {
    private Integer id;
    private String bookName;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\"Book\":{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"bookName\":\"")
                .append(bookName).append('\"');
        sb.append(",\"author\":\"")
                .append(author).append('\"');
        sb.append("}}");
        return sb.toString();
    }
}
