package ${packageFirst}.${package}.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import ${packageFirst}.${package}.entity.${className};
import ${packageFirst}.${package}.vo.${className}VO;
import ${packageFirst}.${package}.service.${className}Service;

/** 
 * Description: ${classNameLower}控制层
 * Created on ${date}
 * @author  ${author}
 */
@Controller
@RequestMapping("/${classNameLower}Controller")
public class ${className}Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(${className}Controller.class);

    @Autowired
	private ${className}Service ${classNameLower}Service;
	
}
