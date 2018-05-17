
package com.schwertz.carrentalservice.interfaces.shared;

/**
 * Contains input validation groups. Grouping strategy based on JSR-303.
 *
 * @author mani
 */
public interface ValidationGroup {

  /**
   * This interface represents a group that contains new things.
   */
  public static interface New {
  }

  /**
   * This interface represents a group that contains existing things.
   */
  public static interface Existing {
  }
}
