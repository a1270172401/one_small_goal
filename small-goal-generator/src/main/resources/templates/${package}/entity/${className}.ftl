package ${packageFirst}.${package}.entity;

import java.io.Serializable;
import lombok.Data;
/**
 * ${table.tableDesc}实体类
 * Created on ${date}
 * @author ${author}
 */
@Data
public class ${className}  implements Serializable {

    private static final long serialVersionUID = 1L;

<#list table.columns as column>
    /**
     * ${column.label}
     */
    private ${column.type} ${column.name};
</#list>
}
