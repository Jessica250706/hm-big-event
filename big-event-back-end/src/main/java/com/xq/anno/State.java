package com.xq.anno;

import com.xq.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 元注解
@Target({ ElementType.FIELD }) // 元注解
@Retention(RetentionPolicy.RUNTIME) // 元注解
@Constraint(
        validatedBy = { StateValidation.class } // 指定提供校验规则的类
)
public @interface State {
    // 提供校验失败后的提示信息
    String message() default "state 参数的值只能是已发布或者草稿";
    // 制定分组
    Class<?>[] groups() default {};
    // 负载，获取到 State 注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
