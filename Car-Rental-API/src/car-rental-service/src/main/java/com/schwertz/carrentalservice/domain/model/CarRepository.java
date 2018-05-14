
package com.schwertz.carrentalservice.domain.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that provides access to {@link Car} repository
 * (technically database or some other storage media).
 *
 * @author mani
 */
public interface CarRepository extends JpaRepository<Car, String> {

  /**
   * Finds cars by given location.
   *
   * @param location
   * @return
   */
  public List<Car> findByLocationIgnoreCase(String location);

  /**
   * Finds cars by given category.
   *
   * @param category
   * @return
   */
  public List<Car> findByCategoryIgnoreCase(String category);

  /**
   * Finds cars by given location and category.
   *
   * @param location
   * @param category
   * @return
   */
  public List<Car> findByLocationAndCategoryIgnoreCase(String location,
	  String category);

}
