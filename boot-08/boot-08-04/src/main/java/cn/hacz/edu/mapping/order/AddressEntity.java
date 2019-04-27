package cn.hacz.edu.mapping.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/4/24 时间:15:59
 * @JDK 1.8
 * @Description 功能模块：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_address")
@DynamicInsert
@DynamicUpdate
public class AddressEntity {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     *  属性描述：name
     */
    private String name;
}
