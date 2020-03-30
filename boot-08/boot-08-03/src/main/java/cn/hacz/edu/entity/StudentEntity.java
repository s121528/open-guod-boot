package cn.hacz.edu.entity;

import cn.hacz.edu.entity.base.Base01Entity;
import cn.hacz.edu.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

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
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_student_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "del_flag=0")
public class StudentEntity extends Base01Entity {
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
