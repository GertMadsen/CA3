
package com.schwertz.carrentalservice.interfaces.web.rest.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schwertz.carrentalservice.interfaces.shared.CheckDateRange;
import com.schwertz.carrentalservice.interfaces.shared.CheckStartingDate;
import com.schwertz.carrentalservice.interfaces.shared.ValidationGroup;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

/**
 * DTO class used for presenting and transferring reservation resource.
 *
 * @author mani
 */
@CheckDateRange(from = "fromDate", to = "toDate", message = "Reservation has invalid date range.", groups = {ValidationGroup.Existing.class, Default.class})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDto {

  private Long id;

  @JsonProperty("companyTag")
  @Size(min = 2, max = 45, message = "Company tag length must be within a range of 2 to 45.", groups = {ValidationGroup.Existing.class, Default.class})
  private String providerTag;

  @NotBlank(message = "Customer mail is required.")
  @Email(message = "Invalid customer mail address.", groups = {ValidationGroup.Existing.class, Default.class})
  private String customerMail;

  @NotNull(message = "From is required.")
  @JsonFormat(pattern = "dd/MM/yyyy")
  @CheckStartingDate(message = "Invalid from date.", groups = {ValidationGroup.Existing.class, Default.class})
  private LocalDate fromDate;

  @NotNull(message = "To is required.")
  @JsonFormat(pattern = "dd/MM/yyyy")
  @CheckStartingDate(message = "Invalid to date.", groups = {ValidationGroup.Existing.class, Default.class})
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Fluent builder class that helps building {@link ReservationDto} in easier
   * way.
   */
  public static class Builder {

    private Long id;
    private String providerTag;
    private String customerMail;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Builder id(Long id) {

      this.id = id;
      return this;
    }

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

    this.id = builder.id;
    this.customerMail = builder.customerMail;
    this.fromDate = builder.fromDate;
    this.providerTag = builder.providerTag;
    this.toDate = builder.toDate;
  }

  @Override
  public String toString() {
    return "ReservationDto{" + "id=" + id + ", providerTag=" + providerTag + ", customerMail=" + customerMail + ", fromDate=" + fromDate + ", toDate=" + toDate + '}';
  }
}
