
package com.schwertz.carrentalservice.interfaces.web.rest.controllers;

import com.schwertz.carrentalservice.application.ReservationService;
import com.schwertz.carrentalservice.domain.model.Reservation;
import com.schwertz.carrentalservice.interfaces.shared.ValidationGroup;
import com.schwertz.carrentalservice.interfaces.web.rest.dtos.ReservationDto;
import com.schwertz.carrentalservice.interfaces.web.rest.shared.DtoBuilder;
import com.schwertz.carrentalservice.interfaces.web.rest.shared.EntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles client request/response specific to reservation
 * resource.
 *
 * @author mani
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  private static final Logger LOGGER = LoggerFactory.getLogger(
	  ReservationController.class);

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private DtoBuilder dtoBuilder;
  @Autowired
  private EntityBuilder entityBuilder;

  /**
   * Handles client's requuest/response of updating reservation resource.
   *
   * @return
   */
  @PutMapping(path = "/{reservationId}")
  public ReservationDto updateReservation(@PathVariable Long reservationId,
	  @RequestBody @Validated(ValidationGroup.Existing.class) ReservationDto reservationDto) {

    reservationDto.setId(reservationId);

    LOGGER.info("Received a request to update a reservation {}.", reservationDto);
    Reservation updatedReservations = this.reservationService.updateReservationInfo(
	    this.entityBuilder.buildReservation(reservationDto));

    return this.dtoBuilder.buildReservationDto(updatedReservations);
  }
}
