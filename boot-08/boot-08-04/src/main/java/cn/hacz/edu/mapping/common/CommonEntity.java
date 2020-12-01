package cn.hacz.edu.mapping.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * project - GitHub整理
 *
 * @author guodd
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateStr;
    private Date date01;
    private java.sql.Date date02;
    private LocalDateTime dateTime;
    private LocalDate localDate;
    private LocalTime localTime;
}
