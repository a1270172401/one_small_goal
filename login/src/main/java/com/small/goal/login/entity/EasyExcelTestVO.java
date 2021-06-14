package com.small.goal.login.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 测试生成EasyExcel实体类
 *
 * @author 念着倒才子傻
 */
@Data
public class EasyExcelTestVO {
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年纪")
    private Integer age;

    @ExcelProperty("出生日期")
    private Date time;
}
