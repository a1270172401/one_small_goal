package ${packageFirst}.${package}.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import ${packageFirst}.${package}.service.${className}Service;

/** 
 * Description: ${classNameLower}控制层
 * Created on ${date}
 * @author  ${author}
 */
@RestController
@RequestMapping("/${classNameLower}Controller")
public class ${className}Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(${className}Controller.class);

    @Autowired
	private ${className}Service ${classNameLower}Service;
	
}
