package com.turkcell.crm.customer_service.annotations.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthDateValidator.class)
public @interface BirthDate {
    String message() default "İçinde bulunduğumuz yıldan büyük olamaz.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
