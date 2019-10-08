package cn.hacz.edu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guodd
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMapping {
    private String field;

    private String type;

    private int participle;

    private int ignoreAbove;
}
