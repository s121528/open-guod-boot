package cn.hacz.edu.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/5/23 时间:20:03
 * @since 1.8
 */
@Getter
@Setter
@ToString
public class DogEntity {
    /**
     * 功能描述：主键id
     */
    private Integer id;

    /**
     * 属性描述：姓名
     */
    private String name;

    /**
     * 功能描述：年龄
     */
    private Integer age;

    /**
     * 功能描述：生日
     */
    private Date birthday;
}