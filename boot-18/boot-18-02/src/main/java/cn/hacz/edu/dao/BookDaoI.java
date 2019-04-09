package cn.hacz.edu.dao;

import cn.hacz.edu.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/3 时间:9:00
 * @JDK 1.8
 * @Description 功能模块：
 */
@Repository
public interface BookDaoI extends ElasticsearchRepository<Book, Serializable> {
}
