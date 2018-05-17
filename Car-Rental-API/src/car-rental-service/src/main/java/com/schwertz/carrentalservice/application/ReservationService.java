
package com.schwertz.carrentalservice.application;

import com.schwertz.carrentalservice.domain.model.Reservation;
import java.util.List;

/**
 * Service interface that provides {@link Reservation} domain entity specific
 * services.
 *
 * @author mani
 */
public interface ReservationService {

  /**
   * Queries for current and upcoming reservations of given car.
   *
   * @param carRegistrationNumber
   * @return {@link List} of {@link Reservation}
   */
  public List<Reservation> queryForCurrentAndUpcomingReservations(
	  String carRegistrationNumber);

  /**
   * Update information of a reservation.
   *
   * @return
   */
  public Reservation updateReservationInfo(Reservation reservation);
}
