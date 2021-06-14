package ${packageFirst};

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * Description: ${classNameLower}VO类
 * Created on ${date}
 * @author  ${author}
 */
@Data
@ApiModel(value = "${classNameLower}VO类",description = "${classNameLower}VO类")
public class ${taxon}VO implements Serializable {

    private static final long serialVersionUID = 1L;

<#list specials as special>

    /**
     * ${special.fieldComment}
     */
    @ApiModelProperty(value = "${special.fieldComment}",name = "${special.aimingField}")
    @JsonProperty("${special.aimingField}")
    private ${special.type} ${special.originalField};

</#list>
}
