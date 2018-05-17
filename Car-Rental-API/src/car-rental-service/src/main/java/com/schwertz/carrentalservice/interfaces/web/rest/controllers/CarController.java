
package com.schwertz.carrentalservice.interfaces.web.rest.controllers;

import com.schwertz.carrentalservice.application.CarService;
import com.schwertz.carrentalservice.application.ReservationService;
import com.schwertz.carrentalservice.domain.model.Car;
import com.schwertz.carrentalservice.domain.model.NotSupportedException;
import com.schwertz.carrentalservice.domain.model.Reservation;
import com.schwertz.carrentalservice.interfaces.shared.CheckDateRangeParameter;
import com.schwertz.carrentalservice.interfaces.web.rest.dtos.CarDto;
import com.schwertz.carrentalservice.interfaces.web.rest.dtos.PayloadDto;
import com.schwertz.carrentalservice.interfaces.web.rest.dtos.ReservationDto;
import com.schwertz.carrentalservice.interfaces.web.rest.shared.DtoBuilder;
import com.schwertz.carrentalservice.interfaces.web.rest.shared.EntityBuilder;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles client request/response specific to car resource.
 *
 * @author mani
 */
@RestController
@RequestMapping("/api/cars")
@Validated
public class CarController {

  private static final Logger LOGGER = LoggerFactory.getLogger(
	  CarController.class);

  @Autowired
  private CarService carService;
  @Autowired
  private ReservationService reservationService;

  @Autowired
  private DtoBuilder dtoBuilder;
  @Autowired
  private EntityBuilder entityBuilder;

  /**
   * Handles client's request/response of getting cars. Client's can also use
   * this interface to search via cars. All parameters are optional. If
   * provided, then finds cars based on provided parameters.
   * <ul>
   * <li>If location is provided, then finds cars that are available only in
   * given location.</li>
   * <li>If category is provided, then finds cars that are available only in
   * given category.</li>
   * <li>If start and end is provided, then finds cars that are available only
   * within given date range. If one of the date is not provided, then this
   * doesn't make a date range. So error message is thrown.</li>
   * </ul>
   * This parameters further can be combined to filter more for cars.
   *
   * @param location
   * @param category
   * @param start
   * @param end
   * @return
   */
  @GetMapping
  @CheckDateRangeParameter
  public PayloadDto getCars(
	  @RequestParam(required = false) String location,
	  @RequestParam(required = false) String category,
	  @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate start,
	  @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate end) {

    LOGGER.info(
	    "Received a request to get cars available in location {}, category {}, and date range from {} to {}.",
	    location, category, start, end);

    List<Car> foundCars;
    if (location != null && category != null && start != null && end != null) {

      foundCars = this.carService.queryForCarsInGivenLocationCategoryAndDateRange(
	      location.trim(), category.trim(), start, end);
    } else if (location != null && start != null && end != null) {
      foundCars = this.carService.queryForCarsInGivenLocationAndDateRange(
	      location.trim(), start, end);
    } else if (category != null && start != null && end != null) {

      foundCars = this.carService.queryForCarsInGivenCategoryAndDateRange(
	      category.trim(), start, end);
    } else if (start != null && end != null) {

      foundCars = this.carService.queryForCarsInGivenDateRange(start, end);
    } else if (location != null && category != null) {

      throw new NotSupportedException(
	      "Quering cars with location and category not supported yet.");
    } else if (location != null) {

      foundCars = this.carService.queryForCarsInGivenLocation(location.trim());
    } else if (category != null) {

      foundCars = this.carService.queryForCarsInGivenCategory(category.trim());
    } else {

      foundCars = this.carService.queryForAllCars();
    }

    LOGGER.debug(
	    "Found {} cars. Now getting their current and future reservations.",
	    foundCars.size());
    List<CarDto> carDtos = this.dtoBuilder.buildCarDtos(foundCars);
    for (CarDto carDto : carDtos) {
      List<Reservation> foundReservations = this.reservationService.queryForCurrentAndUpcomingReservations(
	      carDto.getRegno());
      carDto.setReservationDtos(this.dtoBuilder.buildReservationDtos(
	      foundReservations));
    }
    return new PayloadDto.Builder().carDtos(carDtos).build();
  }

  /**
   * Handles client's request/response of getting an specific prescribed car.
   *
   * @param registrationNumber Registration number of a car.
   * @return {@link CarDto} including only current and future {@link List} of
   * {@link ReservationDto}.
   */
  @GetMapping(path = "/{registrationNumber}")
  public CarDto getCar(
	  @PathVariable @Pattern(regexp = "^[Ll][A-Za-z]{2}[0-9]{4}$", message = "Invalid registration no.") String registrationNumber) {

    LOGGER.info("Received a request to get a car identified as {}.",
	    registrationNumber);

    Car foundCar = this.carService.queryForCarIdentifiedByRegistrationNumber(
	    registrationNumber);

    LOGGER.debug("Car found. Now getting its current and future reservations.");
    List<Reservation> foundReservations = this.reservationService.queryForCurrentAndUpcomingReservations(
	    foundCar.getRegno());
    CarDto carDto = this.dtoBuilder.buildCarDto(foundCar);
    carDto.setReservationDtos(this.dtoBuilder.buildReservationDtos(
	    foundReservations));

    return carDto;
  }

  /**
   * Handles client's requuest/response of updating car resource and adding new
   * reservation resource for that car.
   *
   * @param registrationNumber Car registration number.
   * @param carDto {@link CarDto} including only current and future {@link List}
   * of {@link ReservationDto}.
   * @return
   */
  @PutMapping(path = "/{registrationNumber}")
  public CarDto updateCar(
	  @PathVariable @Pattern(regexp = "^[Ll][A-Za-z]{2}[0-9]{4}$", message = "Invalid registration no.") String registrationNumber,
	  @RequestBody @Valid CarDto carDto) {

    LOGGER.info("Received a request to update car.");
    carDto.setRegno(registrationNumber);

    Car updatedCar = this.carService.updateCarInfo(this.entityBuilder.buildCar(
	    carDto));

    List<Reservation> foundReservations = this.reservationService.queryForCurrentAndUpcomingReservations(
	    updatedCar.getRegno());
    carDto = this.dtoBuilder.buildCarDto(updatedCar);
    carDto.setReservationDtos(this.dtoBuilder.buildReservationDtos(
	    foundReservations));

    return carDto;
  }
}
