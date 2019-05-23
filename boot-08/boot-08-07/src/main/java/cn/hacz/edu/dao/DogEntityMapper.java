package cn.hacz.edu.dao;

import cn.hacz.edu.entity.DogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/5/23 时间:20:03
 * @JDK 1.8
 * @Description 功能模块：
 */
public interface DogEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DogEntity record);

    int insertSelective(DogEntity record);

    DogEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DogEntity record);

    int updateByPrimaryKey(DogEntity record);

    List<DogEntity> findByNameAndAge(@Param("name") String name, @Param("age") Integer age);

    List<Integer> findAgeByName(@Param("name") String name);

    List<String> findNameByAge(@Param("age") Integer age);

}