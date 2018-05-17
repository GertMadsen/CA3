
package com.schwertz.carrentalservice.domain.model;

/**
 * Thrown by services when stated entity which was supposed to exist was not
 * found.
 *
 * @author mani
 */
public class NotSupportedException extends RuntimeException {

  public NotSupportedException(String message) {
    super(message);
  }

}
