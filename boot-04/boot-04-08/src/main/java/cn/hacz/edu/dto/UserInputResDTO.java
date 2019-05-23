package cn.hacz.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/5/22 时间:13:43
 * @JDK 1.8
 * @Description 功能模块：
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInputResDTO {
    /**
     * 属性描述：姓名
     */
    private String name;
    /**
     * 功能描述：年龄
     */
    private Integer age;
}
