
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
 * Annotated element will be marked to check validity of manufactured year.
 * Manufactured year cannot be more then current year or may cannot be before
 * 1970.
 *
 * @author mani
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckManufacturedYear.ManufacturedYearValidator.class)
@Documented
public @interface CheckManufacturedYear {

  String message() default "Invalid car manufactured year.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Manufactured year validation.Manufactured year cannot be more then current
   * year or may cannot be before 1970.
   */
  public static class ManufacturedYearValidator implements ConstraintValidator<CheckManufacturedYear, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext cvc) {

      return !(value < 1970 || value > LocalDate.now().getYear());
    }
  }
}
