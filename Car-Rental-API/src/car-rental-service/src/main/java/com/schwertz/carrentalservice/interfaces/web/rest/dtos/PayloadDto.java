
package com.schwertz.carrentalservice.interfaces.web.rest.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;

/**
 * Data transfer object of response payload. This class is created and used to
 * meet the response payload format standard requirement.
 *
 * @author mani
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayloadDto {

  private Collection<String> errors;
  @JsonProperty("cars")
  private Collection<CarDto> carDtos;

  public Collection<String> getErrors() {
    return errors;
  }

  public void setErrors(Collection<String> errors) {
    this.errors = errors;
  }

  public Collection<CarDto> getCarDtos() {
    return carDtos;
  }

  public void setCarDtos(Collection<CarDto> carDtos) {
    this.carDtos = carDtos;
  }

  /**
   * Fluent builder pattern used to create payload dto since it's impossible via
   * constructor.
   */
  public static class Builder {

    private Collection<String> errors;
    private Collection<CarDto> carDtos;

    public Builder errors(Collection<String> errors) {

      this.errors = errors;
      return this;
    }

    public Builder carDtos(Collection<CarDto> carDtos) {

      this.carDtos = carDtos;
      return this;
    }

    public PayloadDto build() {

      return new PayloadDto(this);
    }
  }

  /**
   * Private constructor only meant for to use by {@link Builder}.
   *
   * @param builder {@link Builder}
   */
  private PayloadDto(Builder builder) {

    this.errors = builder.errors;
    this.carDtos = builder.carDtos;
  }
}
