package com.daishaowen.test.controller.Form;

import com.daishaowen.test.validator.GroupA;
import com.daishaowen.test.validator.GroupB;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

/**
 * Created by disvenk.dai on 2018-09-11 17:06
 */

@Getter
@Setter
//@Data
//@NoArgsConstructor
public class UserForm {
    @NotNull(message = "id不能为空")
    @NotBlank(message = "id不能为空")
    @Email
    public String id;

    @NotNull(message = "姓名不能为空")
    @NotEmpty(message = "姓名不能为空")
    public String name;

}
