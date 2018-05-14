
package com.schwertz.carrentalservice.interfaces.shared;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 *
 * Annotation element will be marked to check where starting date contains past
 * date or not. It doesn't makes sense that starting date is past date.
 *
 * @author mani
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckStartingDate.StartingDateValidator.class)
@Documented
public @interface CheckStartingDate {

  String message() default "Invalid starting date.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Starting date validator. Validates that starting date doesn't contains past
   * date.
   */
  public static class StartingDateValidator implements ConstraintValidator<CheckStartingDate, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext cvc) {

      return !((LocalDate) value).isBefore(LocalDate.now());
    }
  }
}
