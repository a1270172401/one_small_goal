package common;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 特殊实体类映射文件
 *
 * @author 念着倒才子傻
 */
@Data
public class Special {
    /**
     * 目标字段名
     */
    @ExcelProperty(value = "目标字段名")
    private String aimingField;
    /**
     * 目标字段类型
     */
    @ExcelProperty(value = "目标字段类型")
    private String type;
    /**
     * 数据库对应实体类字段名
     */
    @ExcelProperty(value = "数据库对应实体类字段名")
    private String originalField;
    /**
     * 字段备注
     */
    @ExcelProperty(value = "字段备注")
    private String fieldComment;
    /**
     * 类名称
     */
    @ExcelProperty(value = "类名称")
    private String taxon;

    @Override
    public String toString() {
        return "Special{" +
                "type='" + type + '\'' +
                ", aimingField='" + aimingField + '\'' +
                ", originalField='" + originalField + '\'' +
                ", fieldComment='" + fieldComment + '\'' +
                ", taxon='" + taxon + '\'' +
                '}';
    }
}
