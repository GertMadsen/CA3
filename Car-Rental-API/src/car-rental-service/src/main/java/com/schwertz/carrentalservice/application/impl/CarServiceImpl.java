
package com.schwertz.carrentalservice.application.impl;

import com.schwertz.carrentalservice.application.CarService;
import com.schwertz.carrentalservice.domain.model.Car;
import com.schwertz.carrentalservice.domain.model.CarRepository;
import com.schwertz.carrentalservice.domain.model.OverlappingReservationException;
import com.schwertz.carrentalservice.domain.model.Reservation;
import com.schwertz.carrentalservice.domain.model.ReservationRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Implementation class of {@link CarService}.
 *
 * @author mani
 */
@Service
@Validated
public class CarServiceImpl implements CarService {

  private static final Logger LOGGER = LoggerFactory.getLogger(
	  CarServiceImpl.class);

  @Autowired
  private CarRepository carRepository;
  @Autowired
  private ReservationRepository reservationRepository;

  /**
   * {@inheritDoc }
   */
  @Override
  public Car queryForCarIdentifiedByRegistrationNumber(String registrationNumber) {

    return this.carRepository.getOne(registrationNumber);
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public List<Car> queryForAllCars() {

    return this.carRepository.findAll();
  }

  /**
   * {@inheritDoc }
   */
  @Override
  @Transactional
  public Car updateCarInfo(Car car) {

    // Performing business rule validation on car.
    Car foundCar = this.carRepository.getOne(car.getRegno());

    // Updating car.
    foundCar.setAircondition(
	    car.getAircondition() == null ? foundCar.getAircondition() : car.getAircondition());
    foundCar.setCompany(
	    car.getCompany() == null ? foundCar.getCompany() : car.getCompany());
    foundCar.setDoors(
	    car.getDoors() == null ? foundCar.getDoors() : car.getDoors());
    foundCar.setGear(
	    car.getGear() == null ? foundCar.getGear() : car.getGear());
    foundCar.setLogo(
	    car.getLogo() == null ? foundCar.getLogo() : car.getLogo());
    foundCar.setMake(
	    car.getMake() == null ? foundCar.getMake() : car.getMake());
    foundCar.setModel(
	    car.getModel() == null ? foundCar.getModel() : car.getModel());
    foundCar.setPicture(
	    car.getPicture() == null ? foundCar.getPicture() : car.getPicture());
    foundCar.setPriceperday(
	    car.getPriceperday() == null ? foundCar.getPriceperday() : car.getPriceperday());
    foundCar.setSeats(
	    car.getSeats() == null ? foundCar.getSeats() : car.getSeats());
    foundCar.setYear(
	    car.getYear() == null ? foundCar.getYear() : car.getYear());
    foundCar.setCategory(
	    car.getCategory() == null ? foundCar.getCategory() : car.getCategory());
    foundCar.setLocation(
	    car.getLocation() == null ? foundCar.getLocation() : car.getLocation());

    // Performing business rule validation on reservation.
    if (car.getReservations() == null || car.getReservations().isEmpty()) {
      LOGGER.debug("No additional reservations found.");
      return foundCar;
    }

    LOGGER.debug("{} new reservations found.", car.getReservations().size());
    // Getting all current and future servations.
    List<Reservation> foundReservations = this.reservationRepository.findByToDateGreaterThanEqualAndCarRegnoIgnoreCase(
	    LocalDate.now(), car.getRegno());
    for (Reservation foundReservation : foundReservations) {
      for (Reservation reservation : car.getReservations()) {
	if (reservation.getFromDate().isEqual(foundReservation.getFromDate()) || reservation.getFromDate().isEqual(
		foundReservation.getToDate()) || (reservation.getFromDate().isAfter(
			foundReservation.getFromDate()) && reservation.getFromDate().isBefore(
			foundReservation.getToDate()))) {
	  LOGGER.info(
		  "Reservation from {} to {} is overlapping with existing reservation {}.",
		  reservation.getFromDate(), reservation.getToDate(),
		  foundReservation.getId());
	  throw new OverlappingReservationException(reservation.getFromDate(),
		  reservation.getToDate());
	}
	if (reservation.getToDate().isEqual(foundReservation.getFromDate()) || reservation.getToDate().isEqual(
		foundReservation.getToDate()) || (reservation.getToDate().isAfter(
			foundReservation.getFromDate()) && reservation.getToDate().isBefore(
			foundReservation.getToDate()))) {
	  LOGGER.info(
		  "Reservation from {} to {} is overlapping with existing reservation {}.",
		  reservation.getFromDate(), reservation.getToDate(),
		  foundReservation.getId());
	  throw new OverlappingReservationException(reservation.getFromDate(),
		  reservation.getToDate());
	}
      }
    }

    // No business rule violated. So registering new reservations.
    for (Reservation reservation : car.getReservations()) {
      reservation.setCar(foundCar);
    }
    foundCar.getReservations().addAll(car.getReservations());

    return foundCar;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public List<Car> queryForCarsInGivenLocationCategoryAndDateRange(
	  String location, String category, LocalDate from, LocalDate to) {

    List<Car> filteredCars = new ArrayList<>();

    // Getting cars filtered by location and category.
    List<Car> foundCars = this.carRepository.findByLocationAndCategoryIgnoreCase(
	    location, category);

    for (Car car : foundCars) {

      boolean overlaps = false;
      // Getting current and upcoming reseravations.
      List<Reservation> foundReservations = this.reservationRepository.findByToDateGreaterThanEqualAndCarRegnoIgnoreCase(
	      LocalDate.now(),
	      car.getRegno());
      // If empty, car is available in any current and future date.
      if (foundReservations.isEmpty()) {
	filteredCars.add(car);
	continue;
      }

      // Looping via every reseravation to check availability.
      for (Reservation foundReservation : foundReservations) {
	if (from.isEqual(foundReservation.getFromDate()) || from.isEqual(
		foundReservation.getToDate()) || (from.isAfter(
			foundReservation.getFromDate()) && from.isBefore(
			foundReservation.getToDate()))) {
	  overlaps = true;
	  break;
	}
	if (to.isEqual(foundReservation.getFromDate()) || to.isEqual(
		foundReservation.getToDate()) || (to.isAfter(
			foundReservation.getFromDate()) && to.isBefore(
			foundReservation.getToDate()))) {
	  overlaps = true;
	  break;
	}
      }

      // If car didn't overlapped in any of existing reservation. That means car is available.
      if (!overlaps) {
	filteredCars.add(car);
      } else {
	LOGGER.debug("Given date range is overlapping existing reservations.");
      }
    }
    return filteredCars;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public List<Car> queryForCarsInGivenLocationAndDateRange(String location,
	  LocalDate from, LocalDate to) {

    List<Car> filteredCars = new ArrayList<>();

    // Getting cars filtered by location.
    List<Car> foundCars = this.carRepository.findByLocationIgnoreCase(
	    location);

    for (Car car : foundCars) {

      boolean overlaps = false;
      // Getting current and upcoming reseravations.
      List<Reservation> foundReservations = this.reservationRepository.findByToDateGreaterThanEqualAndCarRegnoIgnoreCase(
	      LocalDate.now(),
	      car.getRegno());
      // If empty, car is available in any current and future date.
      if (foundReservations.isEmpty()) {
	filteredCars.add(car);
	continue;
      }

      // Looping via every reseravation to check availability.
      for (Reservation foundReservation : foundReservations) {
	if (from.isEqual(foundReservation.getFromDate()) || from.isEqual(
		foundReservation.getToDate()) || (from.isAfter(
			foundReservation.getFromDate()) && from.isBefore(
			foundReservation.getToDate()))) {
	  overlaps = true;
	  break;
	}
	if (to.isEqual(foundReservation.getFromDate()) || to.isEqual(
		foundReservation.getToDate()) || (to.isAfter(
			foundReservation.getFromDate()) && to.isBefore(
			foundReservation.getToDate()))) {
	  overlaps = true;
	  break;
	}
      }

      // If car didn't overlapped in any of existing reservation. That means car is available.
      if (!overlaps) {
	filteredCars.add(car);
      } else {
	LOGGER.debug("Given date range is overlapping existing reservations.");
      }
    }
    return filteredCars;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public List<Car> queryForCarsInGivenCategoryAndDateRange(String category,
	  LocalDate from, LocalDate to) {

    List<Car> filteredCars = new ArrayList<>();

    // Getting cars filtered by category.
    List<Car> foundCars = this.carRepository.findByCategoryIgnoreCase(
	    category);

    for (Car car : foundCars) {

      boolean overlaps = false;
      // Getting current and upcoming reseravations.
      List<Reservation> foundReservations = this.reservationRepository.findByToDateGreaterThanEqualAndCarRegnoIgnoreCase(
	      LocalDate.now(),
	      car.getRegno());
      // If empty, car is available in any current and future date.
      if (foundReservations.isEmpty()) {
	filteredCars.add(car);
	continue;
      }

      // Looping via every reseravation to check availability.
      for (Reservation foundReservation : foundReservations) {
	if (from.isEqual(foundReservation.getFromDate()) || from.isEqual(
		foundReservation.getToDate()) || (from.isAfter(
			foundReservation.getFromDate()) && from.isBefore(
			foundReservation.getToDate()))) {
	  overlaps = true;
	  break;
	}
	if (to.isEqual(foundReservation.getFromDate()) || to.isEqual(
		foundReservation.getToDate()) || (to.isAfter(
			foundReservation.getFromDate()) && to.isBefore(
			foundReservation.getToDate()))) {
	  overlaps = true;
	  break;
	}
      }

      // If car didn't overlapped in any of existing reservation. That means car is available.
      if (!overlaps) {
	filteredCars.add(car);
      } else {
	LOGGER.debug("Given date range is overlapping existing reservations.");
      }
    }
    return filteredCars;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public List<Car> queryForCarsInGivenDateRange(
	  LocalDate from, LocalDate to) {

    List<Car> filteredCars = new ArrayList<>();

    // Getting all cars.
    List<Car> foundCars = this.carRepository.findAll();

    for (Car car : foundCars) {

      boolean overlaps = false;
      // Getting current and upcoming reseravations.
      List<Reservation> foundReservations = this.reservationRepository.findByToDateGreaterThanEqualAndCarRegnoIgnoreCase(
	      LocalDate.now(),
	      car.getRegno());
      // If empty, car is available in any current and future date.
      if (foundReservations.isEmpty()) {
	filteredCars.add(car);
	continue;
      }

      // Looping via every reseravation to check availability.
      for (Reservation foundReservation : foundReservations) {
	if (from.isEqual(foundReservation.getFromDate()) || from.isEqual(
		foundReservation.getToDate()) || (from.isAfter(
			foundReservation.getFromDate()) && from.isBefore(
			foundReservation.getToDate()))) {
	  overlaps = true;
	  break;
	}
	if (to.isEqual(foundReservation.getFromDate()) || to.isEqual(
		foundReservation.getToDate()) || (to.isAfter(
			foundReservation.getFromDate()) && to.isBefore(
			foundReservation.getToDate()))) {
	  overlaps = true;
	  break;
	}
      }

      // If car didn't overlapped in any of existing reservation. That means car is available.
      if (!overlaps) {
	filteredCars.add(car);
      } else {
	LOGGER.debug("Given date range is overlapping existing reservations.");
      }
    }
    return filteredCars;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public List<Car> queryForCarsInGivenLocation(String location) {

    return this.carRepository.findByLocationIgnoreCase(location);
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public List<Car> queryForCarsInGivenCategory(String category) {

    return this.carRepository.findByCategoryIgnoreCase(category);
  }
}
