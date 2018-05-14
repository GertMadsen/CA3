
package com.schwertz.carrentalservice.domain.model;

/**
 * Documents categories of cars.
 *
 * @author mani
 */
public enum Category {

  M("Mini"),
  N("Mini Elite"),
  E("Economy"),
  H("Economy Elite"),
  C("Compact"),
  D("Compact Elite"),
  I("Intermediate"),
  J("Intermediate Elite"),
  S("Standard"),
  R("Standard Elite"),
  F("Fullsize"),
  G("Fullsize Elite"),
  P("Premium"),
  U("Premium Elite"),
  L("Luxury"),
  W("Luxury Elite"),
  O("Oversize"),
  X("Special");

  private final String name;

  private Category(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  /**
   * Finds {@link Category} by their name.
   *
   * @param name
   * @return
   */
  public static Category findByName(String name) {

    for (Category category : Category.values()) {
      if (category.getName().equalsIgnoreCase(name.trim())) {
	return category;
      }
    }

    return null;
  }
}
