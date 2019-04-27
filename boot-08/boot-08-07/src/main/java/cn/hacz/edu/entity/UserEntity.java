package cn.hacz.edu.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/25 时间:13:18
 * @JDK 1.8
 * @Description 功能模块：
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
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
     * 功能描述：班级号
     */
    private Integer classNum;
    /**
     * 出生日期
     */
    private LocalDateTime birthday;
}
