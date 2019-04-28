package cn.hacz.edu.modules.system.entity;

import cn.hacz.edu.modules.system.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/11 时间:15:32
 * @JDK 1.8
 * @Description 功能模块：
 */
@Data
@Entity
@Table(name = "tb_student_jpa")
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity extends BaseEntity {
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
