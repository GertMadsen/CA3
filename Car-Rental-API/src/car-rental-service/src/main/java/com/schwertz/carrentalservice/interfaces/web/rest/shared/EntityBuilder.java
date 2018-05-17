
package com.schwertz.carrentalservice.interfaces.web.rest.shared;

import com.schwertz.carrentalservice.domain.model.Car;
import com.schwertz.carrentalservice.domain.model.Reservation;
import com.schwertz.carrentalservice.interfaces.web.rest.dtos.CarDto;
import com.schwertz.carrentalservice.interfaces.web.rest.dtos.ReservationDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Builder class that assist in building entity from dto received from a
 * request.
 *
 * @author mani
 */
@Component
public class EntityBuilder {

  /**
   * Builds {@link List} of {@link Car} from {@link List} of {@link CarDto}.
   *
   * @param carDtos
   * @return
   */
  public List<Car> buildCars(List<CarDto> carDtos) {

    List<Car> cars = new ArrayList<>();
    for (CarDto carDto : carDtos) {
      cars.add(this.buildCar(carDto));
    }
    return cars;
  }

  /**
   * Builds {@link Car} from {@link CarDto}.
   *
   * @param carDto
   * @return
   */
  public Car buildCar(CarDto carDto) {

    return new Car.Builder().regno(carDto.getRegno()).aircondition(
	    carDto.getAircondition()).category(
		    carDto.getCategory().trim()).doors(carDto.getDoors()).gear(
	    carDto.getGear().trim()).picture(carDto.getPicture().trim()).location(
	    carDto.getLocation().trim()).make(carDto.getMake().trim()).year(
	    carDto.getYear()).model(carDto.getModel().trim()).priceperday(
	    carDto.getPriceperday()).seats(carDto.getSeats()).company(
	    carDto.getCompany().trim()).logo(carDto.getLogo().trim()).reservations(
	    carDto.getReservationDtos() != null ? this.buildReservations(
			    carDto.getReservationDtos()) : null).build();
  }

  /**
   * Builds {@link Reservation} from {@link List} of {@link ReservationDto}.
   *
   * @param reservationDto
   * @return
   */
  public Reservation buildReservation(ReservationDto reservationDto) {

    return new Reservation.Builder().id(reservationDto.getId()).companyTag(
	    reservationDto.getProviderTag()).customerMail(
		    reservationDto.getCustomerMail()).fromDate(
		    reservationDto.getFromDate()).toDate(
		    reservationDto.getToDate()).build();
  }

  /**
   * Builds {@link List} of {@link Reservation} from {@link List} of
   * {@link ReservationDto}.
   *
   * @param reservationDtos
   * @return
   */
  public List<Reservation> buildReservations(
	  List<ReservationDto> reservationDtos) {

    List<Reservation> reservations = new ArrayList<>();
    for (ReservationDto reservationDto : reservationDtos) {
      reservations.add(this.buildReservation(reservationDto));
    }
    return reservations;
  }
}
