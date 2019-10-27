package cn.hacz.edu.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDto implements Serializable {
    /**
     * Id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String content;

    /**
     * 描述
     */
    private String describe;
}
