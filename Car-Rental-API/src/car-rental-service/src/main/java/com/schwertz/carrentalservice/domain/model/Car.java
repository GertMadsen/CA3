
package com.schwertz.carrentalservice.domain.model;

import com.schwertz.carrentalservice.domain.shared.CheckCategory;
import com.schwertz.carrentalservice.domain.shared.CheckLocation;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Entity class representing car in domain model.
 *
 * @author mani
 */
@Entity
@Table(name = "cars")
public class Car {

  @Id
  @Column(length = 7, nullable = false)
  @GenericGenerator(name = "regno-generator", strategy = "com.schwertz.carrentalservice.domain.model.RegistrationNumberGenerator")
  @GeneratedValue(generator = "regno-generator")
  private String regno;

  @Column(length = 254, nullable = false)
  private String logo;

  @Column(length = 45, nullable = false)
  private String company;

  @Column(length = 45, nullable = false)
  private String companyTag;

  @Column(length = 45, nullable = false)
  @CheckCategory
  private String category;

  @Column(length = 254, nullable = false)
  private String picture;

  @Column(length = 45, nullable = false)
  private String make;

  @Column(length = 45, nullable = false)
  private String model;

  @Column(length = 4, nullable = false)
  private Integer year;

  @Column(length = 45, nullable = false)
  @CheckLocation
  private String location;

  @Column(nullable = false)
  private Double priceperday;

  @Column(length = 2, nullable = false)
  private Integer seats;

  @Column(length = 1, nullable = false)
  private Integer doors;

  @Column(length = 45, nullable = false)
  private String gear;

  @Column(nullable = false)
  private Boolean aircondition;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "regno", referencedColumnName = "regno")
  private List<Reservation> reservations;

  public Car() {
  }

  public String getRegno() {
    return regno;
  }

  public void setRegno(String regno) {
    this.regno = regno;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Double getPriceperday() {
    return priceperday;
  }

  public void setPriceperday(Double priceperday) {
    this.priceperday = priceperday;
  }

  public Integer getSeats() {
    return seats;
  }

  public void setSeats(Integer seats) {
    this.seats = seats;
  }

  public Integer getDoors() {
    return doors;
  }

  public void setDoors(Integer doors) {
    this.doors = doors;
  }

  public String getGear() {
    return gear;
  }

  public void setGear(String gear) {
    this.gear = gear;
  }

  public Boolean getAircondition() {
    return aircondition;
  }

  public void setAircondition(Boolean aircondition) {
    this.aircondition = aircondition;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  public String getCompanyTag() {
    return companyTag;
  }

  public void setCompanyTag(String companyTag) {
    this.companyTag = companyTag;
  }

  /**
   * Fluent builder class that helps building {@link Car} in easier way.
   */
  public static class Builder {

    private String logo;
    private String company;
    private String category;
    private String picture;
    private String make;
    private String model;
    private Integer year;
    private String regno;
    private String location;
    private Double priceperday;
    private Integer seats;
    private Integer doors;
    private String gear;
    private Boolean aircondition;
    private List<Reservation> reservations;

    public Builder logo(String logo) {

      this.logo = logo;
      return this;
    }

    public Builder company(String company) {

      this.company = company;
      return this;
    }

    public Builder category(String category) {

      this.category = category;
      return this;
    }

    public Builder picture(String picture) {

      this.picture = picture;
      return this;
    }

    public Builder make(String make) {

      this.make = make;
      return this;
    }

    public Builder model(String model) {

      this.model = model;
      return this;
    }

    public Builder year(Integer year) {

      this.year = year;
      return this;
    }

    public Builder regno(String regno) {

      this.regno = regno;
      return this;
    }

    public Builder location(String location) {

      this.location = location;
      return this;
    }

    public Builder priceperday(Double priceperday) {

      this.priceperday = priceperday;
      return this;
    }

    public Builder seats(Integer seats) {

      this.seats = seats;
      return this;
    }

    public Builder doors(Integer doors) {

      this.doors = doors;
      return this;
    }

    public Builder gear(String gear) {

      this.gear = gear;
      return this;
    }

    public Builder aircondition(Boolean aircondition) {

      this.aircondition = aircondition;
      return this;
    }

    public Builder reservations(List<Reservation> reservations) {

      this.reservations = reservations;
      return this;
    }

    public Car build() {

      return new Car(this);
    }
  }

  /**
   * Private constructor only meant to use by {@link Car} builder.
   *
   * @param builder
   */
  private Car(Builder builder) {

    this.aircondition = builder.aircondition;
    this.category = builder.category;
    this.company = builder.company;
    this.doors = builder.doors;
    this.gear = builder.gear;
    this.picture = builder.picture;
    this.location = builder.location;
    this.logo = builder.logo;
    this.make = builder.make;
    this.year = builder.year;
    this.model = builder.model;
    this.priceperday = builder.priceperday;
    this.regno = builder.regno;
    this.reservations = builder.reservations;
    this.seats = builder.seats;
  }
}
