package ${packageFirst}.${package}.mapper;

import ${packageFirst}.${package}.entity.${className};
import ${packageFirst}.${package}.utils.Page;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * Description: ${table.tableDesc}mapper
 * Created on ${date}
 * @author  ${author}
 */
@Mapper
public interface ${className}Mapper  extends BaseMapper<${className}> {

    List<${className}> selectByNo();

}
