package io.wisoft.foodie.project.global.web.validator.image;

import org.springframework.context.annotation.Conditional;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImageContentTypeValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Image {

    String message() default "이미지 파일이 아닙니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
