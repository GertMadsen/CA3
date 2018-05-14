
package com.schwertz.carrentalservice.application.impl;

import com.schwertz.carrentalservice.application.ReservationService;
import com.schwertz.carrentalservice.domain.model.Reservation;
import com.schwertz.carrentalservice.domain.model.ReservationRepository;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation class of {@link ReservationService}.
 *
 * @author mani
 */
@Service
public class ReservationServiceImpl implements ReservationService {

  private static Logger LOGGER = LoggerFactory.getLogger(
	  ReservationServiceImpl.class);

  @Autowired
  private ReservationRepository reservationRepository;

  /**
   * {@inheritDoc }
   */
  @Override
  public List<Reservation> queryForCurrentAndUpcomingReservations(
	  String carRegistrationNumber) {
    return this.reservationRepository.findByToDateGreaterThanEqualAndCarRegnoIgnoreCase(
	    LocalDate.now(), carRegistrationNumber);
  }
}
