package cn.hacz.edu.mapping.one2one;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * project - GitHub整理 一对一映射（单向外键、双向外键、单向主键、双向主键、联合主键、联合组件映射）
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2018/11/21 时间:20:49
 * @JDK 1.8
 * @Description 功能模块：丈夫，数据库只有一种关系：外键；以妻子为主导的双向外键关联
 * a)	transient：内存中一个对象，没ID,缓存中也没有
 * b)	persistent：内存中有，缓存中有，数据库有（ID)
 * c)	detached：内存有，缓存没有，数据库有，ID
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "map_husband")
@DynamicInsert
@DynamicUpdate
public class HusbandEntity {
    /**
     * 功能描述：主机id
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 属性描述：姓名
     */
    private String name;
    /**
     * 属性描述：妻子
     */
    @OneToOne
    @JoinColumn(name = "wife_id")
    private WifeEntity wife;
}
