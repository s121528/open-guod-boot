package cn.hacz.edu.controller;

import cn.hacz.edu.dao.BookDaoI;
import cn.hacz.edu.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/5/8 时间:20:43
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestController
public class BookController {
    @Autowired
    private BookDaoI bookDaoI;

    @GetMapping(value = "/create")
    public String create() {
        Book book = new Book();
        book.setAuthor("guodd");
        book.setBookName("guodd");
        return bookDaoI.save(book).toString();
    }

    @GetMapping("/get")
    public String getAll() {
        Iterable<Book> iterable = bookDaoI.findAll();
        List<Book> list = new ArrayList<>();
        iterable.forEach(list::add);
        System.out.println(list);
        return "成功！";
    }
}
