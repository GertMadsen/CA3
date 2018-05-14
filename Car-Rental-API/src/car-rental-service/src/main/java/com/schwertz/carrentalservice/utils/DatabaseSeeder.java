
package com.schwertz.carrentalservice.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.schwertz.carrentalservice.domain.model.Car;
import com.schwertz.carrentalservice.domain.model.CarRepository;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class that helps to seed/populate database. Useful for demo as well as for
 * some data that are doesn't frequently changes and known.
 *
 * @author mani
 */
@Component
public class DatabaseSeeder {

  private static final Logger LOGGER = LoggerFactory.getLogger(
	  DatabaseSeeder.class);

  @Autowired
  private CarRepository carRepository;

  @Value("${schwertz.car-data:../conf/cars.xml}")
  private String carDataFilePath;

  @Autowired
  private Validator validator;

  private XmlMapper xmlMapper = new XmlMapper();

  /**
   * Populates database with needed data.
   */
  public void seed() {

    this.seedCars();
  }

  /**
   * Populate database with cars.
   */
  @Transactional
  private void seedCars() {

    // If there's cars in its repository then database alreay populated.
    if (this.carRepository.count() > 0) {
      return;
    }

    // Creating and popluating xml to objects.
    List<Car> cars;
    try {
      cars = this.xmlMapper.readValue(new FileInputStream(
	      this.carDataFilePath), new TypeReference<List<Car>>() {
      });
    } catch (IOException ex) {
      LOGGER.error(ex.getMessage());
      return;
    }

    // Validating input requirements.
    if (cars.isEmpty()) {
      LOGGER.info("No cars to populate.");
      return;
    }
    Set<ConstraintViolation<List<Car>>> validationErrors = this.validator.validate(
	    cars, Default.class);
    if (!validationErrors.isEmpty()) {
      validationErrors.stream().forEach(error -> {
	LOGGER.error(error.getMessage());
      });
      return;
    }

    // Changing input dto to entity to persist.
    for (Car car : cars) {
      this.carRepository.save(car);
    }
  }
}
