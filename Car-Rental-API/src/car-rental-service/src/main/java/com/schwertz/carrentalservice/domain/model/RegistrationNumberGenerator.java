
package com.schwertz.carrentalservice.domain.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Registration no generator class. Generates registration no using following
 * rules: 1. Contains 3 letters a the beginning with L always being the first
 * letter. 2. Contains 4 digits afterwards.
 *
 * Following algorithm has been used:
 * </p>
 * <ul>
 * <li>1. Gets last inserted registration no. from storage.</li>
 * <li>2. If none found, then inserts very first registration no.
 * "LAA0001".</li>
 * <li>3. If found, then checks if digits in registration no. can be
 * incremented. The limit is 9999. If can, then only increments the digit to
 * form new registration no.</li>
 * <li>4. If digit has reached the limit, then digit gets reset to 1. Instead
 * letters will be incremented.</li>
 * <li>5. Checks last letter has reached to limit. The limit of letter is Z. If
 * not, then last letter is incremented concatening first letters last reseted
 * digit forming a new registration no.</li>
 * <li>6. If last letter has reached the limit, then last character gets reset
 * too to A. Instead second last letter will be incremented.</li>
 * <li>7. Checks second last letter has reached the limit. If reached, then
 * cannot generate new reg no at all. So throws
 * {@link RegistrationNumberLimitReachedException} exception.</li>
 * <li>8. If second last letter has not reached the limit, then it is
 * incremented concatening first letter, last reseted letter, and reseted digit
 * forming a new registration no.</li>
 * </ul>
 *
 * @author mani
 */
public class RegistrationNumberGenerator implements IdentifierGenerator {

  private static final Logger LOGGER = LoggerFactory.getLogger(
	  RegistrationNumberGenerator.class);

  @Override
  public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) throws HibernateException {
    
    Connection connection = sharedSessionContractImplementor.connection();
    String sql = "SELECT regno FROM cars ORDER BY regno DESC LIMIT 1";
    try {

      Statement statement = connection.createStatement();

      // Getting last inserted id.
      ResultSet resultSet = statement.executeQuery(sql);
      if (!resultSet.next()) {
	LOGGER.debug("No cars been registered yet! So assigning first id.");
	return "LAA0001";
      }
      String lastRegNo = resultSet.getString("regno");
      LOGGER.debug("Found last registration no: " + lastRegNo);

      // Separating characters with digits from last registration no.
      String lastRegNoString = lastRegNo.substring(0, 3);
      int lastRegNoDigits = Integer.parseInt(lastRegNo.substring(3));

      // If last reg no digit is not 9999, we can increment the number only for next id.
      if (lastRegNoDigits != 9999) {
	return lastRegNoString + String.format("%04d", ++lastRegNoDigits);
      }

      // Since last reg no digit is on max 9999, need to repeat the digit but need to increment letters instead.
      char[] lastRegNoLetters = lastRegNoString.toCharArray();

      // If last letter is not in limit, we can increment last letter only.
      if (lastRegNoLetters[2] != 'Z') {
	return new StringBuilder().append(lastRegNoLetters[0]).append(
		lastRegNoLetters[1]).append(++lastRegNoLetters[2]).append(
		String.format("%04d", 1)).toString();
      }

      // Last letter is in limit, so have to increment second last letter and last letter need to be at starting point again.
      if (lastRegNoLetters[1] != 'Z') {
	return new StringBuilder().append(lastRegNoLetters[0]).append(
		++lastRegNoLetters[1]).append('A').append(
		String.format("%04d", 1)).toString();
      }

      // The combination has been reach its limit. Instead throwing an specific exception.
      throw new RegistrationNumberLimitReachedException(
	      "Registration no. limit reached. Last registration no. is " + lastRegNoString);
    } catch (SQLException ex) {
      LOGGER.error(ex.getMessage());
      throw new HibernateException(ex.getMessage());
    }
  }

  /**
   * Exception instantiated and thrown when generators finds the registration no
   * being reached it's limit and impossible to generate new registration no.
   */
  public static class RegistrationNumberLimitReachedException extends HibernateException {

    public RegistrationNumberLimitReachedException(String message) {
      super(message);
    }
  }
}
