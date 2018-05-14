
package com.schwertz.carrentalservice.domain.shared;

import com.schwertz.carrentalservice.domain.model.Category;
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
 * Annotated element will be marked to check if the given category is supported
 * based on {@link Category}.
 *
 * @author mani
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckCategory.CategoryValidator.class)
@Documented
public @interface CheckCategory {

  String message() default "Unsupported category. Only Mini, Mini Elite, Economy, Economy Elite, Compact, Compact Elite, Intermediate, Intermediate Elite, Standard, Standard Elite, Fullsize, Fullsize Elite, Premium, Premium Elite, Luxury, Luxury Elite, Oversize, and Special are supported at the moment.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Category validator. Validates based on {@link Category}. If given category
   * is in {@link Category}, then it is considered valid. Otherwise invalid.
   */
  public static class CategoryValidator implements ConstraintValidator<CheckCategory, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {

      return Category.findByName(value) != null;
    }
  }
}
