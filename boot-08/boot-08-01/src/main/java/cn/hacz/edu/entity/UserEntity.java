package cn.hacz.edu.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * 用户实体
 *
 * @author guod
 * @version 1.0
 */
@Data
public class UserEntity {
    private String id;
    private String name;
    private Integer age;
    private LocalDate birthday;
}
