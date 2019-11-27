package cn.hacz.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * project - GitHub整理 boot企业级开发
 *
 * @author dong
 * @version 3.0
 * @date 日期:2018/7/3 时间:21:43
 * @JDK 1.8
 * @Description 功能模块：人vo对象
 */
@Data
public class PersonVo {
    private String id;
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;
}
