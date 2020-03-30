package cn.hacz.edu.entity;

import lombok.Data;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/2/25
 * @since 1.8
 */
@Data
public class Message extends BaseEntity {
    /**
     * 属性描述：yonghm
     */
    private String userName;

    /**
     * 属性描述：年龄
     */
    private Integer userAge;
}
