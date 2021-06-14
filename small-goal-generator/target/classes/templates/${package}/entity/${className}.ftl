package ${packageFirst}.${package}.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * ${table.tableDesc}实体类
 * Created on ${date}
 * @author ${author}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("${table.dbName}")
public class ${className}  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键id
    */
    @TableId(type = IdType.AUTO)
    private Long id;

<#list table.columns as column>
    /**
     * ${column.label}
     */
    @TableField(value = "${column.dbName}")
    private ${column.type} ${column.name};
</#list>
}
