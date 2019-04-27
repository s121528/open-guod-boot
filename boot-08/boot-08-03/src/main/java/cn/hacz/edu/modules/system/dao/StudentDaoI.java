package cn.hacz.edu.modules.system.dao;

import cn.hacz.edu.modules.system.entity.StudentEntity;
import cn.hacz.edu.modules.system.vo.StudentVo;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/11 时间:15:35
 * @JDK 1.8
 * @Description 功能模块：
 */
public interface StudentDaoI extends JpaRepository<StudentEntity, Integer> {
    /*===========================基础查询==============================*/
    //根据firstName与LastName查找(两者必须在数据库有)
    StudentEntity findByFirstNameAndLastName(String firstName, String lastName);

    //根据firstName或LastName查找(两者其一有就行)
    StudentEntity findByLastNameOrFirstName(String lastName, String firstName);

    //根据firstName查找它是否存在数据库里<类似与以下关键字>
    //StudentEntity findByFirstName(String firstName);
    StudentEntity findByFirstNameIs(String firstName);

    //在Age数值age到age2之间的数据
    List<StudentEntity> findByAgeBetween(Integer age, Integer age2);

    //小于指定age数值之间的数据
    List<StudentEntity> findByAgeLessThan(Integer age);

    //小于等于指定age数值的数据
    List<StudentEntity> findByAgeLessThanEqual(Integer age);

    //大于指定age数值之间的数据
    List<StudentEntity> findByAgeGreaterThan(Integer age);

    //大于或等于指定age数值之间的数据
    List<StudentEntity> findByAgeGreaterThanEqual(Integer age);

    //在指定age数值之前的数据类似关键字<LessThan>
    List<StudentEntity> findByAgeAfter(Integer age);

    //在指定age数值之后的数据类似关键字<GreaterThan>
    List<StudentEntity> findByAgeBefore(Integer age);

    //返回age字段为空的数据
    List<StudentEntity> findByAgeIsNull();

    //返回age字段不为空的数据
    List<StudentEntity> findByAgeNotNull();

    /**
     * 该关键字我一度以为是类似数据库的模糊查询,
     * 但是我去官方文档看到它里面并没有通配符。
     * 所以我觉得它类似
     * StudentEntity findByFirstName(String firstName);
     *
     * @see https://docs.spring.io/spring-data/jpa/docs/2.1.5.RELEASE/reference/html/#jpa.repositories
     */
    StudentEntity findByFirstNameLike(String firstName);

    //同上
    List<StudentEntity> findByFirstNameNotLike(String firstName);

    //查找数据库中指定类似的名字(如：输入一个名字"M" Jpa会返回多个包含M开头的名字的数据源)<类似数据库模糊查询>
    List<StudentEntity> findByFirstNameStartingWith(String firstName);

    //查找数据库中指定不类似的名字(同上)
    List<StudentEntity> findByFirstNameEndingWith(String firstName);

    //查找包含的指定数据源(这个与以上两个字段不同的地方在与它必须输入完整的数据才可以查询)
    List<StudentEntity> findByFirstNameContaining(String firstName);

    //根据age选取所有的数据源并按照LastName进行升序排序
    List<StudentEntity> findByAgeOrderByLastName(Integer age);

    //返回不是指定age的所有数据
    List<StudentEntity> findByAgeNot(Integer age);

    //查找包含多个指定age返回的数据
    List<StudentEntity> findByAgeIn(List<Integer> age);

    /*===========================动态查询==============================*/
    // 01对象作为参数传输
    @Modifying
    @Transactional
    @Query(" update StudentEntity s set s.age =:#{#studentVo.age} where s.age =:#{#age} ")
    int updateFlowStatus(@Param("studentVo") StudentVo studentVo, @Param("age") int age);

    @Query(value = " from StudentEntity s where s.firstName=:#{#studentVo.firstName} and s.age=:#{#studentVo.age} ")
    List<StudentEntity> findParamsVo(@Param("studentVo") StudentVo studentVo);

    // 02字符串作为参数
    @Modifying
    @Transactional
    @Query(" update StudentEntity s set s.firstName =:firstName where s.lastName =:lastName ")
    int updateTransId(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // 03参数定位分页
    @Query(" from StudentEntity s where s.firstName = ?1 and s.lastName like ?2 and s.age=100 ")
    Page<StudentEntity> queryByParams(@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable pageable);

    // 04原生SQL
    @Query(value = " select * from tb_student_jpa t where t.id =:id ", nativeQuery = true)
    String getPlayUrls(@Param("id") String id);

    // 05Example_实例查询
    ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith()) // 姓名采用“开始匹配”的方式查询
            .withIgnorePaths("int");  // 忽略属性：是否关注。因为是基本类型，需要忽略掉

    /*===========================删除操作==============================*/
    @Transactional
    int deleteByAge(Integer age);

    @Query(" delete from StudentEntity s where s.age = ?1 ")
    @Transactional
    int doDeleteAge(@Param("age") Integer age);
}