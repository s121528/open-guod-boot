package cn.hacz.edu.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author guodd
 * @since 1.0
 */
@Data
public class DeviceMasterExcel implements Serializable {
    /**
     * 属性描述：医院名称
     */
    @Excel(name = "医院")
    private String hospital;

    /**
     * 属性描述：设备ip
     */
    @Excel(name = "设备IP", orderNum = "1")
    private String deviceIp;

    /**
     * 属性描述：设备类别
     */
    @Excel(name = "设备类别", orderNum = "2")
    private String deviceType;

    /**
     * 属性描述：设备别名
     */
    @Excel(name = "设备别名", orderNum = "3")
    private String deviceName;

    /**
     * 属性描述：年份-月份
     */
    @Excel(name = "年份-月份", exportFormat = "yyyyMM", orderNum = "4")
    private String month;

    /**
     * 属性描述：设备收入
     */
    @Excel(name = "设备收入", orderNum = "5")
    private String deviceIncome;

    /**
     * 属性描述：设备检查量
     */
    @Excel(name = "设备检查量", orderNum = "6")
    private Integer checkCount;

    /**
     * 属性描述：异常数据量
     */
    @Excel(name = "异常数据量", orderNum = "7")
    private Integer exceptionCount;

    /**
     * 属性描述：体检量
     */
    @Excel(name = "体检量", orderNum = "8")
    private Integer examinationCount;
}
