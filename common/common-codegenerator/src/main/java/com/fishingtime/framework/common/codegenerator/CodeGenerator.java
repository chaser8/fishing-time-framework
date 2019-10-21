package com.fishingtime.framework.common.codegenerator;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CodeGenerator {
    Class<? extends ICodeGenerator> value();
    String [] params() default {};
}
