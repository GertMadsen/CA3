
package com.schwertz.carrentalservice.domain.model;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that provides access to {@link Reservation} repository
 * (technically database or some other storage media).
 *
 * @author mani
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  /**
   * Gets reservations from repository whose toDate is greater equal to given
   * date and associated car id is equal to given registration no.
   *
   * @param date
   * @return
   */
  public List<Reservation> findByToDateGreaterThanEqualAndCarRegnoIgnoreCase(
	  LocalDate date, String carResitrationNumber);
}
