package cn.hacz.edu.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Project -
 *
 * @author guodd
 * @version 1.0
 * @date 2020/2/25
 * @since 1.8
 */
@Data
public class BaseEntity {
    /**
     * 属性描述：id
     */
    private Integer id;

    /**
     * 属性描述：创建时间
     */
    private LocalDateTime createDatetime;

    private LocalDate localDate;
    private LocalTime localTime;

}
