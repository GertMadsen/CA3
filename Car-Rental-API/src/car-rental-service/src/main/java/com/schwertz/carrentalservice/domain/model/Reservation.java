
package com.schwertz.carrentalservice.domain.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class representing reservation in domain model.
 *
 * @author mani
 */
@Entity
@Table(name = "reservations")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 45, nullable = false)
  private String companyTag;

  @Column(length = 254, nullable = false)
  private String customerMail;

  private LocalDate fromDate;
  private LocalDate toDate;

  @ManyToOne
  @JoinColumn(name = "regno")
  private Car car;

  public Reservation() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCustomerMail() {
    return customerMail;
  }

  public void setCustomerMail(String customerMail) {
    this.customerMail = customerMail;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public String getCompanyTag() {
    return companyTag;
  }

  public void setCompanyTag(String companyTag) {
    this.companyTag = companyTag;
  }

  /**
   * Fluent builder class that helps building {@link Builder} in easier way.
   */
  public static class Builder {

    private Long id;
    private String companyTag;
    private String customerMail;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Builder id(Long id) {

      this.id = id;
      return this;
    }

    public Builder companyTag(String companyTag) {

      this.companyTag = companyTag;
      return this;
    }

    public Builder customerMail(String customerMail) {

      this.customerMail = customerMail;
      return this;
    }

    public Builder fromDate(LocalDate fromDate) {

      this.fromDate = fromDate;
      return this;
    }

    public Builder toDate(LocalDate toDate) {

      this.toDate = toDate;
      return this;
    }

    public Reservation build() {

      return new Reservation(this);
    }
  }

  /**
   * Private constructor only meant to use by {@link Car} builder.
   *
   * @param builder
   */
  private Reservation(Builder builder) {

    this.customerMail = builder.customerMail;
    this.fromDate = builder.fromDate;
    this.id = builder.id;
    this.toDate = builder.toDate;
    this.companyTag = builder.companyTag;
  }

  @Override
  public String toString() {
    return "Reservation{" + "id=" + id + ", companyTag=" + companyTag + ", customerMail=" + customerMail + ", fromDate=" + fromDate + ", toDate=" + toDate + ", car=" + car + '}';
  }
}
