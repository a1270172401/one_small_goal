package common;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 特殊实体类映射文件
 * @author 念着倒才子傻
 */
@Data
public class Special {
    /**
     * 目标字段名
     */
    @ExcelProperty(value = "aimingField")
    private String aimingField;
    /**
     * 目标字段类型
     */
    @ExcelProperty(value = "type")
    private String type;
    /**
     * 数据库字段名
     */
    @ExcelProperty(value = "originalField")
    private String originalField;
    /**
     * 字段备注
     */
    @ExcelProperty(value = "fieldComment")
    private String fieldComment;
    /**
     * 类名称
     */
    @ExcelProperty(value = "taxon")
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
