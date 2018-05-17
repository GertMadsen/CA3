
package com.schwertz.carrentalservice.interfaces.shared;

import com.schwertz.carrentalservice.interfaces.web.rest.dtos.ReservationDto;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * Annotated element must be a collection of reservations that validates and
 * restricts reservations overlappings.
 *
 * @author mani
 */
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoOverlappingReservations.OverlappingReservationsValidator.class)
@Documented
public @interface NoOverlappingReservations {

  String message() default "Supplied reservations are overlapping.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Validator that restricts multiple overlapping reservations. Worst run time
   * complexity is O(nlogn).
   */
  public static class OverlappingReservationsValidator implements ConstraintValidator<NoOverlappingReservations, Collection<ReservationDto>> {

    @Override
    public boolean isValid(Collection<ReservationDto> reservationDtos,
	    ConstraintValidatorContext cvc) {

      // If null or empty, no need to check for overlappings.
      if (reservationDtos == null || reservationDtos.isEmpty() || reservationDtos.size() == 1) {
	return true;
      }

      // Converting to List if not in list for optimized algorithm.
      List<ReservationDto> reservationDtoList;
      if (reservationDtos instanceof List) {
	reservationDtoList = (List<ReservationDto>) reservationDtos;
      } else {
	reservationDtoList = new ArrayList<>(reservationDtos);
      }

      // Sorting by from date.
      Collections.sort(reservationDtoList, Comparator.comparing(
	      ReservationDto::getFromDate));

      // Checking for every to date is not after next reservation start date.
      LocalDate lastEndDate = reservationDtoList.get(0).getToDate();
      for (int index = 1; index < reservationDtoList.size(); index++) {
	if (lastEndDate.isEqual(reservationDtoList.get(index).getFromDate()) || lastEndDate.isAfter(
		reservationDtoList.get(index).getFromDate())) {
	  return false;
	}
      }
      return true;
    }

  }
}
