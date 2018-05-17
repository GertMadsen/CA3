
package com.schwertz.carrentalservice.application.impl;

import com.schwertz.carrentalservice.application.ReservationService;
import com.schwertz.carrentalservice.domain.model.OverlappingReservationException;
import com.schwertz.carrentalservice.domain.model.Reservation;
import com.schwertz.carrentalservice.domain.model.ReservationRepository;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  /**
   * {@inheritDoc }
   */
  @Override
  @Transactional
  public Reservation updateReservationInfo(Reservation reservation) {

    LOGGER.debug("Updating reservation info {}.", reservation);
    LOGGER.trace("Checking if reservation exist or not.");
    Reservation foundReservation = this.reservationRepository.getOne(
	    reservation.getId());

    foundReservation.setCompanyTag(
	    reservation.getCompanyTag() == null ? foundReservation.getCompanyTag() : reservation.getCompanyTag());
    foundReservation.setCustomerMail(
	    reservation.getCustomerMail() == null ? foundReservation.getCustomerMail() : reservation.getCustomerMail());

    LOGGER.trace("Checking if reservation dates are changed.");
    // If date not changed, then don't need to check for overlapping reservations
    if ((reservation.getFromDate() == null && reservation.getToDate() == null) || (reservation.getFromDate().isEqual(
	    foundReservation.getFromDate()) && reservation.getToDate().isEqual(
		    foundReservation.getToDate()))) {
      return foundReservation;
    }

    LOGGER.trace("Getting all existing and upcoming reservations for car {}.",
	    foundReservation.getCar());
    List<Reservation> foundReservations = this.reservationRepository.findByToDateGreaterThanEqualAndCarRegnoIgnoreCaseAndIdNot(
	    LocalDate.now(), foundReservation.getCar().getRegno(),
	    foundReservation.getId()
    );

    LOGGER.trace("Checking for overlappings before storing.");
    for (Reservation currentOrUpcomingReservation : foundReservations) {
      if (currentOrUpcomingReservation.getFromDate().isEqual(
	      reservation.getFromDate()) || currentOrUpcomingReservation.getFromDate().isEqual(
		      reservation.getToDate()) || (currentOrUpcomingReservation.getFromDate().isAfter(
		      reservation.getFromDate()) && currentOrUpcomingReservation.getFromDate().isBefore(
		      reservation.getToDate()))) {
	throw new OverlappingReservationException(reservation.getFromDate(),
		reservation.getToDate());
      }
      if (currentOrUpcomingReservation.getToDate().isEqual(
	      reservation.getFromDate()) || currentOrUpcomingReservation.getToDate().isEqual(
		      reservation.getToDate()) || (currentOrUpcomingReservation.getToDate().isAfter(
		      reservation.getFromDate()) && currentOrUpcomingReservation.getToDate().isBefore(
		      reservation.getToDate()))) {
	throw new OverlappingReservationException(reservation.getFromDate(),
		reservation.getToDate());
      }
    }

    LOGGER.trace("Updating dates too.");
    foundReservation.setFromDate(reservation.getFromDate());
    foundReservation.setToDate(reservation.getToDate());

    LOGGER.trace("Returning updated reservation.");
    return foundReservation;
  }
}
