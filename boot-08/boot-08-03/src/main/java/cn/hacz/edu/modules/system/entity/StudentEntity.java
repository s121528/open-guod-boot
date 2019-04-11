package cn.hacz.edu.modules.system.entity;

import cn.hacz.edu.modules.system.entity.base.Base01Entiy;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * project -
 *
 * @author yanfa07
 * @version 1.0
 * @date 日期:2019/4/11 时间:15:32
 * @JDK 1.8
 * @Description 功能模块：
 */
@Data
@Entity
@Table(name = "tb_student_jpa")
public class StudentEntity extends Base01Entiy {
    /**
     * 属性描述：
     */
    private String firstName;
    /**
     * 属性描述：
     */
    private String lastName;
    /**
     * 属性描述：
     */
    private String sex;
    /**
     * 属性描述：
     */
    private String email;

    /**
     * 属性描述：
     */
    private Integer age;
}
