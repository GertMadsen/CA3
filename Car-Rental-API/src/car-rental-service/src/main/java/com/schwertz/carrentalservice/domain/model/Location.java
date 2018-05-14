
package com.schwertz.carrentalservice.domain.model;

/**
 * Documents possible locations that a car might be at.
 *
 * @author mani
 */
public enum Location {

  COPENHAGEN_AIRPORT("Cph (Copenhagen Airport)"),
  BILLUND_LUFTHAVN("Billund Lufthavn"),
  AALBORG_LUFTHAVN("Aalborg Lufthavn"),
  COPENHAGEN_CITY("Copenhagen City"),
  AARHUS_CITY("Aarhus City"),
  ODENSE("Odense"),
  HERNING("Herning"),
  ROSKILDE("Roskilde"),
  ESBJERG("Esbjerg"),
  NAESTVED("Naestved");

  private final String name;

  private Location(String name) {
    this.name = name;
  }

  public String getName() {

    return this.name;
  }

  /**
   * Finds {@link Location} by its name.
   *
   * @param name
   * @return
   */
  public static Location findByName(String name) {

    for (Location location : Location.values()) {

      if (location.getName().equalsIgnoreCase(name.trim())) {
	return location;
      }
    }

    return null;
  }
}
