package com.small.goal.login.controller;


import com.common.exception.CheckRequestVo;
import com.common.exception.ResultBody;
import com.small.goal.login.anotation.MyHandler;
import com.small.goal.login.entity.ExceptionTestVo;
import com.small.goal.login.service.SmallGoalUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * Description: smallGoalUser控制层
 * Created on 2020年09月24日
 *
 * @author 念着倒才子傻
 */
@RestController
@RequestMapping("/smallGoalUserController")
@Validated
public class SmallGoalUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmallGoalUserController.class);

    @Autowired
    private SmallGoalUserService smallGoalUserService;




    @MyHandler 
    @GetMapping("/getExcelTest")
    public void getExcelTest(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        smallGoalUserService.getExcelTest(request, response);
    }

    @PostMapping("/postTest")
    public Object postTest() {
        return "123";
    }

    @GetMapping("/getTest")
    public String getTest(HttpServletRequest request, HttpServletResponse response) {
        throw new RuntimeException("111");
    }

    @PostMapping("/exceptionTest")
    public ResultBody exceptionTest(@Validated @RequestBody ExceptionTestVo exceptionTestVo, BindingResult bindingResult){
        return ResultBody.success();
    }


}









