
package com.schwertz.carrentalservice.interfaces.web.rest.shared;

import com.schwertz.carrentalservice.domain.model.Car;
import com.schwertz.carrentalservice.domain.model.Reservation;
import com.schwertz.carrentalservice.interfaces.web.rest.dtos.CarDto;
import com.schwertz.carrentalservice.interfaces.web.rest.dtos.ReservationDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Builder class that assist in building dto from entity received from an
 * application.
 *
 * @author mani
 */
@Component
public class DtoBuilder {

  /**
   * Build {@link CarResponseDto} dto from {@link Car} entity.
   *
   * @param car
   * @return
   */
  public CarDto buildCarDto(Car car) {

    return new CarDto.Builder().aircondition(
	    car.getAircondition()).category(
		    car.getCategory()).company(car.getCompany()).doors(
	    car.getDoors()).gear(car.getGear()).logo(car.getPicture()).location(
	    car.getLocation()).logo(car.getLogo()).make(
	    car.getMake()).year(car.getYear()).model(
	    car.getModel()).priceperday(car.getPriceperday()).regno(
	    car.getRegno()).seats(car.getSeats()).picture(car.getPicture()).build();
  }

  /**
   * Build {@link List} of {@link CarResponseDto} dto from {@link List} of
   * {@link Car} entity.
   *
   * @param cars
   * @return
   */
  public List<CarDto> buildCarDtos(List<Car> cars) {

    List<CarDto> carDtos = new ArrayList<>();
    for (Car car : cars) {
      carDtos.add(this.buildCarDto(car));
    }
    return carDtos;
  }

  /**
   * Build {@link ReservationDto} dto from {@link Reservation} entity.
   *
   * @param reservation
   * @return
   */
  public ReservationDto buildReservationDto(Reservation reservation) {

    return new ReservationDto.Builder().id(reservation.getId()).customerMail(
	    reservation.getCustomerMail()).fromDate(reservation.getFromDate()).providerTag(
	    reservation.getCompanyTag()).toDate(reservation.getToDate()).build();
  }

  /**
   * Build {@link List} of {@link ReservationDto} dto from {@link List} of
   * {@link Reservation} entity.
   *
   * @param reservations
   * @return
   */
  public List<ReservationDto> buildReservationDtos(
	  List<Reservation> reservations) {

    List<ReservationDto> reservationDtos = new ArrayList<>();
    for (Reservation reservation : reservations) {
      reservationDtos.add(this.buildReservationDto(reservation));
    }
    return reservationDtos;
  }
}
