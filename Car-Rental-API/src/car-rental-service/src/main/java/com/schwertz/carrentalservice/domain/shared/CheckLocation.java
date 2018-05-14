
package com.schwertz.carrentalservice.domain.shared;

import com.schwertz.carrentalservice.domain.model.Location;
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
 * Annotated element will be marked to check if the given location is supported
 * based on {@link Location}.
 *
 * @author mani
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckLocation.LocationValidator.class)
@Documented
public @interface CheckLocation {

  String message() default "Unsupported location. Only Cph (Copenhagen Airport), Billund Lufthavn, Aalborg Lufthavn, Copenhagen City, Aarhus City, Odense, Herning, Roskilde, Esbjerg, and Naestved are supported at the moment.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Location validator. Validates based on {@link Location}. If given location
   * is in {@link Location}, then it is considered valid. Otherwise invalid.
   */
  public static class LocationValidator implements ConstraintValidator<CheckLocation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {

      return Location.findByName(value) != null;
    }
  }
}
