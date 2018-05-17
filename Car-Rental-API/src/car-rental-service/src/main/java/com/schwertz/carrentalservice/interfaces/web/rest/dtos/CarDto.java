
package com.schwertz.carrentalservice.interfaces.web.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schwertz.carrentalservice.interfaces.shared.CheckManufacturedYear;
import com.schwertz.carrentalservice.interfaces.shared.NoOverlappingReservations;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

/**
 * DTO class used for presenting and transferring car resource.
 *
 * @author mani
 */
public class CarDto {

  private String regno;

  @Size(min = 5, max = 254, message = "Logo length size must be within a range from 5 to 254.")
  @URL(message = "Logo must be of valid url.")
  private String logo;

  @Size(min = 2, max = 45, message = "Company length size is not within the range of 2 to 45.")
  private String company;

  @Size(min = 2, max = 45, message = "Category length size is not within the range of 2 to 45.")
  private String category;

  @Size(min = 5, max = 254, message = "Picture length size is not within the range of 2 to 45.")
  @URL(message = "Picture must be of valid url.")
  private String picture;

  @Size(min = 2, max = 45, message = "Make length size is not within the range of 2 to 45.")
  private String make;

  @Size(min = 2, max = 45, message = "Model length size is not within the range of 2 to 45.")
  private String model;

  @CheckManufacturedYear
  private Integer year;

  @Size(min = 2, max = 45, message = "Location size length is not within the range of 2 to 45.")
  private String location;

  private Double priceperday;

  @Min(value = 1, message = "Invalid number of seats.")
  private Integer seats;

  @Min(value = 1, message = "Invalid number of doors.")
  private Integer doors;

  @Size(min = 2, max = 45, message = "Gear length size is not within the range of 2 to 45.")
  private String gear;

  private Boolean aircondition;

  @JsonIgnore
  private String companyTag;

  @JsonProperty("reservations")
  @Valid
  @NoOverlappingReservations
  private List<ReservationDto> reservationDtos;

  public CarDto() {
  }

  public String getRegno() {
    return regno;
  }

  public void setRegno(String regno) {
    this.regno = regno.trim();
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo.trim();
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company.trim();
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category.trim();
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture.trim();
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make.trim();
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model.trim();
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
    this.location = location.trim();
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
    this.gear = gear.trim();
  }

  public Boolean getAircondition() {
    return aircondition;
  }

  public void setAircondition(Boolean aircondition) {
    this.aircondition = aircondition;
  }

  public List<ReservationDto> getReservationDtos() {
    return reservationDtos;
  }

  public void setReservationDtos(List<ReservationDto> reservationDtos) {
    this.reservationDtos = reservationDtos;
  }

  public String getCompanyTag() {
    return companyTag;
  }

  public void setCompanyTag(String companyTag) {
    this.companyTag = companyTag;
  }

  /**
   * Fluent builder class that helps building {@link CarDto} in easier way.
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
    private String companyTag;
    private List<ReservationDto> reservationDtos;

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

    public Builder companyTag(String companyTag) {

      this.companyTag = companyTag;
      return this;
    }

    public Builder reservationDtos(List<ReservationDto> reservationDtos) {

      this.reservationDtos = reservationDtos;
      return this;
    }

    public CarDto build() {

      return new CarDto(this);
    }
  }

  /**
   * Private constructor only meant to used by {@link Builder}.
   *
   * @param builder {@link Builder}
   */
  private CarDto(Builder builder) {

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
    this.reservationDtos = builder.reservationDtos;
    this.seats = builder.seats;
    this.companyTag = builder.companyTag;
  }
}
