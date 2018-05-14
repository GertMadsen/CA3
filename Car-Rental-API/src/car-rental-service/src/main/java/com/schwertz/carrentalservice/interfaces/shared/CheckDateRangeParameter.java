
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
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 *
 * Compares given dates and validates that target date is not before starting
 * date.
 *
 * @author mani
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckDateRangeParameter.DateRangeParameterValidator.class)
@Documented
public @interface CheckDateRangeParameter {

  String message() default "Invalid date range.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Date range validator. Validates that from date doesn't comes after to date.
   * Customer might reserve car for one day, so same from and to date is ok.
   */
  @SupportedValidationTarget(ValidationTarget.PARAMETERS)
  public static class DateRangeParameterValidator implements ConstraintValidator<CheckDateRangeParameter, Object[]> {

    @Override
    public boolean isValid(Object[] values, ConstraintValidatorContext cvc) {

      if (values[2] == null && values[3] == null) {
	return true;
      }
      if ((values[2] == null && values[3] != null) || (values[2] != null && values[3] == null)) {
	return false;
      }
      return !((LocalDate) values[2]).isAfter((LocalDate) values[3]);
    }
  }
}
