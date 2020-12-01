package cn.hacz.edu.mapping.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CommonResVo {
    private Long id;
    private String dateStr;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date01;
    private java.sql.Date date02;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private LocalDate localDate;
    private LocalTime localTime;
}
