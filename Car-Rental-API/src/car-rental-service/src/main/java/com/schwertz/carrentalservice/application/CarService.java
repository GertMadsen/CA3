
package com.schwertz.carrentalservice.application;

import com.schwertz.carrentalservice.domain.model.Car;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

/**
 * Service interface that provides {@link Car} domain entity specific services.
 *
 * @author mani
 */
public interface CarService {

  /**
   * Queries for all cars.
   *
   * @return {@link List} of {@link Car}
   */
  public List<Car> queryForAllCars();

  /**
   * Queries for a car with given registration number. If not found, then throws
   * {@link EntityNotFoundException}.
   *
   * @param registrationNumber
   * @return {@link List} of {@link Car}
   */
  public Car queryForCarIdentifiedByRegistrationNumber(String registrationNumber);

  /**
   * Updates a car information. If new reservations are found, then adds them
   * too.
   *
   * @param car {@link Car}
   * @return {@link List} of {@link Car}
   */
  public Car updateCarInfo(@Valid Car car);

  /**
   * Queries for cars available in given location, category, and date range.
   *
   * @param location
   * @param category
   * @param from
   * @param to
   * @return {@link List} of {@link Car}
   */
  public List<Car> queryForCarsInGivenLocationCategoryAndDateRange(
	  String location, String category, LocalDate from, LocalDate to);

  /**
   * Queries for cars available in given location, and date range.
   *
   * @param location
   * @param from
   * @param to
   * @return {@link List} of {@link Car}
   */
  public List<Car> queryForCarsInGivenLocationAndDateRange(
	  String location, LocalDate from, LocalDate to);

  /**
   * Queries for cars available in given category, and date range.
   *
   * @param category
   * @param from
   * @param to
   * @return {@link List} of {@link Car}
   */
  public List<Car> queryForCarsInGivenCategoryAndDateRange(
	  String category, LocalDate from, LocalDate to);

  /**
   * Queries for cars available in given date range.
   *
   * @param from
   * @param to
   * @return {@link List} of {@link Car}
   */
  public List<Car> queryForCarsInGivenDateRange(
	  LocalDate from, LocalDate to);

  /**
   * Queries for cars available in give location.
   *
   * @param location
   * @return {@link List} of {@link Car}
   */
  public List<Car> queryForCarsInGivenLocation(
	  String location);

  /**
   * Queries for cars available in give category.
   *
   * @param category
   * @return {@link List} of {@link Car}
   */
  public List<Car> queryForCarsInGivenCategory(
	  String category);
}
