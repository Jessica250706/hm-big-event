package com.xq.validation;

import com.xq.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// ConstraintValidator<给哪个注解提供校验规则, 校验的数据类型>
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     *
     * @param string 将来要校验的数据
     * @param constraintValidatorContext
     * @return 如果返回 false，则校验不通过；如果返回 true，则校验通过；
     */
    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        // 提供校验规则
        if (string == null) {
            return false;
        }
        return string.equals("已发布") || string.equals("草稿");
    }
}
