package ${packageFirst}.${package}.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * ${table.tableDesc}返回参数
 * Created on ${date}
 * @author ${author}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ${className}Res  implements Serializable {
    private static final long serialVersionUID = 1L;

<#list table.columns as column>

    @ApiModelProperty(value = "${column.label}")
    private ${column.type} ${column.name};
</#list>
}
