
package com.schwertz.carrentalservice.domain.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Test class that contains test cases and implementations for testing
 * registration no generation algorithm.
 *
 * @author mani
 */
public class RegistrationNumberGeneratorTest {

  @Mock
  private SharedSessionContractImplementor sharedSessionContractImplementor;
  @Mock
  private Connection connection;
  @Mock
  private Statement statement;
  @Mock
  private ResultSet resultSet;

  private RegistrationNumberGenerator registrationNumberGenerator = new RegistrationNumberGenerator();

  @Before
  public void init() throws SQLException {

    MockitoAnnotations.initMocks(this);

    Mockito.when(this.sharedSessionContractImplementor.connection()).thenReturn(
	    this.connection);
    Mockito.when(this.connection.createStatement()).thenReturn(this.statement);
    Mockito.when(this.statement.executeQuery(Mockito.anyString())).thenReturn(
	    this.resultSet);
  }

  @Test
  public void testGenerate_WithNoCars_ShouldReturnFirstRegNo() throws SQLException {

    Mockito.when(this.resultSet.next()).thenReturn(Boolean.FALSE);

    String regNo = (String) this.registrationNumberGenerator.generate(
	    this.sharedSessionContractImplementor,
	    this);

    Assert.assertEquals(true, "LAA0001".equalsIgnoreCase(regNo));
  }

  @Test
  public void testGenerate_WithLastRegNo_WithLimitNotReachedDigit_ShouldReturnRegNoIncrementingOnlyDigits() throws SQLException {

    Mockito.when(this.resultSet.next()).thenReturn(Boolean.TRUE);
    Mockito.when(this.resultSet.getString(Mockito.anyString())).thenReturn(
	    "LAA0001");

    String regNo = (String) this.registrationNumberGenerator.generate(
	    this.sharedSessionContractImplementor,
	    this);

    Assert.assertEquals(true, "LAA0002".equalsIgnoreCase(regNo));
  }

  @Test
  public void testGenerate_WithLastRegNo_WithLimitReachedDigit_ShouldReturnRegNoIncrementingLastLetter() throws SQLException {

    Mockito.when(this.resultSet.next()).thenReturn(Boolean.TRUE);
    Mockito.when(this.resultSet.getString(Mockito.anyString())).thenReturn(
	    "LAA9999");

    String regNo = (String) this.registrationNumberGenerator.generate(
	    this.sharedSessionContractImplementor,
	    this);

    Assert.assertEquals(true, "LAB0001".equalsIgnoreCase(regNo));
  }

  @Test
  public void testGenerate_WithLastRegNo_WithLimitReachedLastLetter_ShouldReturnRegNoIncrementingSecondLastLetter() throws SQLException {

    Mockito.when(this.resultSet.next()).thenReturn(Boolean.TRUE);
    Mockito.when(this.resultSet.getString(Mockito.anyString())).thenReturn(
	    "LAZ9999");

    String regNo = (String) this.registrationNumberGenerator.generate(
	    this.sharedSessionContractImplementor,
	    this);

    Assert.assertEquals(true, "LBA0001".equalsIgnoreCase(regNo));
  }

  @Test(expected = RegistrationNumberGenerator.RegistrationNumberLimitReachedException.class)
  public void testGenerate_WithLastRegNo_WithAllLimitReached_ShouldGetAnException() throws SQLException {

    Mockito.when(this.resultSet.next()).thenReturn(Boolean.TRUE);
    Mockito.when(this.resultSet.getString(Mockito.anyString())).thenReturn(
	    "LZZ9999");
    this.registrationNumberGenerator.generate(
	    this.sharedSessionContractImplementor,
	    this);
  }
}
