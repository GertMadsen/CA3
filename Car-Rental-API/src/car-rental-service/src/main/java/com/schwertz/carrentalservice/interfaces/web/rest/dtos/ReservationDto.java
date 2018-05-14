
package com.schwertz.carrentalservice.interfaces.web.rest.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schwertz.carrentalservice.interfaces.shared.CheckDateRange;
import com.schwertz.carrentalservice.interfaces.shared.CheckStartingDate;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO class used for presenting and transferring reservation resource.
 *
 * @author mani
 */
@CheckDateRange(from = "fromDate", to = "toDate", message = "Reservation has invalid date range.")
public class ReservationDto {

  @JsonProperty("companyTag")
  private String providerTag;

  @NotBlank(message = "Customer mail is required.")
  @Email(message = "Invalid customer mail address.")
  private String customerMail;

  @NotNull(message = "From is required.")
  @JsonFormat(pattern = "dd/MM/yyyy")
  @CheckStartingDate(message = "Invalid from date.")
  private LocalDate fromDate;

  @NotNull(message = "To is required.")
  @JsonFormat(pattern = "dd/MM/yyyy")
  @CheckStartingDate(message = "Invalid to date.")
  private LocalDate toDate;

  public ReservationDto() {
  }

  public String getProviderTag() {
    return providerTag;
  }

  public void setProviderTag(String providerTag) {
    this.providerTag = providerTag.trim();
  }

  public String getCustomerMail() {
    return customerMail;
  }

  public void setCustomerMail(String customerMail) {
    this.customerMail = customerMail.trim();
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

  /**
   * Fluent builder class that helps building {@link ReservationDto} in easier
   * way.
   */
  public static class Builder {

    private String providerTag;
    private String customerMail;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Builder providerTag(String providerTag) {

      this.providerTag = providerTag;
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

    public ReservationDto build() {

      return new ReservationDto(this);
    }
  }

  /**
   * Private constructor only meant for to use by {@link Builder}.
   *
   * @param builder {@link Builder}
   */
  private ReservationDto(Builder builder) {

    this.customerMail = builder.customerMail;
    this.fromDate = builder.fromDate;
    this.providerTag = builder.providerTag;
    this.toDate = builder.toDate;
  }
}
