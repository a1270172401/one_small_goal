package ${packageFirst}.${package}.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ${packageFirst}.${package}.utils.Page;
import ${packageFirst}.${package}.mapper.${className}Mapper;
import ${packageFirst}.${package}.entity.${className};
import ${packageFirst}.${package}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Description: ${table.tableDesc}服务实现
 * Created on ${date}
 * @author ${author}
 */
@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(${className}ServiceImpl.class);


	@Autowired
	private ${className}Mapper ${classNameLower}Mapper;


}