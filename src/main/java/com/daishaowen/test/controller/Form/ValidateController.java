package com.daishaowen.test.controller.Form;

import com.daishaowen.test.chinaMobile.annotation.JsonDto;
import com.daishaowen.test.chinaMobile.annotation.ValidDto;
import com.daishaowen.test.chinaMobile.exception.BusinessException;
import com.daishaowen.test.chinaMobile.exception.ExceptionEnum;
import com.daishaowen.test.chinaMobile.exception.ExceptionType;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by disvenk.dai on 2018-09-11 17:08
 */

@Validated
@RestController
@RequestMapping("validate")
public class ValidateController {


    //如果是json形式必须前端的请求是json，如果有不符合的参数报400且不进入全局异常
    //BindingResult result必须加在形参中
    @RequestMapping("addUser")
    public String addUser(@RequestBody @Valid final UserForm userForm,BindingResult result){
        throw new BusinessException(ExceptionEnum.EXECUTE_RUNTIME_EXCP.setExceptionMsg("出现异常"));
        //System.out.println(userForm.id+":"+userForm.name);
        //return "success";
    }

    //如果传入的参数都为空则不进入,可以使用接口拼接参数但不能使用{}json格式
    @RequestMapping(value = "/demo3")
    public void demo3(
                      @NotNull(message = "年级不能为空")
                      //@NotBlank(message = "年级不能为空,")
                      @Range(min = 1, max = 9, message = "年级只能从1-9")
                      @RequestParam(name = "grade", required = true)
                              Integer grade,
                      @NotNull(message = "班级不能为空")
                      //@NotBlank(message = "班级不能为空,")
                      @Min(value = 1, message = "班级最小只能1")
                      @Max(value = 99, message = "班级最大只能99")
                      @RequestParam(name = "classroom", required = true)
                              Integer classroom) {
        System.out.println(grade + "," + classroom);
    }

}
