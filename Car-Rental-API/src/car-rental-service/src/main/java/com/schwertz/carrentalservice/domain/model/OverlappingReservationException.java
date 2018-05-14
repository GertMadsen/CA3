
package com.schwertz.carrentalservice.domain.model;

import java.time.LocalDate;

/**
 * Thrown by services when supplied reservation command date is overlapping with
 * existing reservations.
 *
 * @author mani
 */
public class OverlappingReservationException extends RuntimeException {

  public OverlappingReservationException(LocalDate from, LocalDate to) {

    super("Cannot make reservation from " + from + " to " + to + ". Car is already reserved within those date range.");
  }
}
