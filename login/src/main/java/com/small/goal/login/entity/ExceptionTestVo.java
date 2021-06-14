package com.small.goal.login.entity;

import com.common.exception.BizException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class ExceptionTestVo implements Serializable {

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotBlank(message = "年龄不能为空")
    private String age;



}
