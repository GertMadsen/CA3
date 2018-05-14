
package com.schwertz.carrentalservice.interfaces.shared;

import com.schwertz.carrentalservice.interfaces.web.rest.dtos.ReservationDto;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 *
 * Compares given dates and validates that target date is not before starting
 * date.
 *
 * @author mani
 */
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckDateRange.DateRangeValidator.class)
@Documented
public @interface CheckDateRange {

  String message() default "Invalid date range.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  public String from();

  public String to();

  /**
   * Date range validator. Validates that from date doesn't comes after to date.
   * Customer might reserve car for one day, so same from and to date is ok.
   */
  public static class DateRangeValidator implements ConstraintValidator<CheckDateRange, Object> {

    private String fromFieldName;
    private String toFieldName;

    @Override
    public void initialize(CheckDateRange constraintAnnotation) {

      this.fromFieldName = constraintAnnotation.from();
      this.toFieldName = constraintAnnotation.to();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext cvc) {

      if (value instanceof ReservationDto) {
	ReservationDto reservationDto = (ReservationDto) value;
	return !reservationDto.getFromDate().isAfter(reservationDto.getToDate());
      }
      return true;
    }
  }
}
