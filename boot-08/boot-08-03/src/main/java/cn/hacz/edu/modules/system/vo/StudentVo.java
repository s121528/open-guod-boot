package cn.hacz.edu.modules.system.vo;

import lombok.Data;

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
public class StudentVo {
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
