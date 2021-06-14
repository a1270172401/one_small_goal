package ${packageFirst}.${package}.vo;

import lombok.Data;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * ${table.tableDesc}请求参数
 * Created on ${date}
 * @author ${author}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ${className}Req  implements Serializable {
    private static final long serialVersionUID = 1L;

<#list table.columns as column>

    @ApiModelProperty(value = "${column.label}",name = "${column.label}",required = true)
    private ${column.type} ${column.name};
</#list>
}
