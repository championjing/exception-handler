package com.ctbri.tool.error.annotation;

import org.springframework.context.annotation.Import;
import com.ctbri.tool.error.GlobalControllerAdvice;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GlobalControllerAdvice.class)
public @interface EnableExceptionHandler {
}
