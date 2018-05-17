import React, { Component } from "react";
import { Link } from 'react-router-dom'
import facade from "../apiFacade";
import getFormattedDate from "../Components/DateFormatter";

export default class Confirmation extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fetchURL: props.fetchURL, dataFromServer: [],
      firstname: props.firstname, lastname: props.lastname, email: props.email,
      car: props.car,
      fromDate: props.start, toDate: props.end,
    };
    var car = this.state.car;
    var reservations = car.reservations;
    var insertion = { companyTag: "Carmondo", customerMail: this.state.email, fromDate: this.state.fromDate, toDate: this.state.toDate }
    reservations.push(insertion);
    car.reservations = reservations;
    let startDate = getFormattedDate(this.state.fromDate)
    let endDate = getFormattedDate(this.state.toDate)
    var body = {
      car: car,
      booking: { regno: car.regno, fromDate: startDate, toDate: endDate, companyTag: "Carmondo" },
      customer: { email: this.state.email, firstName: this.state.firstname, lastName: this.state.lastname }
    };
    this.state.body = body;
  }
  componentDidMount() {
    facade.fetchBooking(this.state.body).then(res => this.setState({ dataFromServer: res }));
  }
  render() {
    return (
      <div className="row">
        <div className="col-sm-4"></div>
        <div className="col-sm-4 ">
          <br /><br /><br />
          <div className="nonTransparent text-center textColor rounded">
            <div className=""> <h3> Confirmation</h3> </div>
            <p> Mr./Mrs. <b>{this.state.firstname} {this.state.lastname}</b> </p>
            <p> Your reservation for a <b>{this.state.car.make} {this.state.car.model}</b> </p>
            <p> from <b>{getFormattedDate(this.state.fromDate)}</b> to <b>{getFormattedDate(this.state.toDate)}</b></p>
            <p> Company: <b>{this.state.car.company} </b> </p>
            <p> located at <b>{this.state.car.location}</b> has been completed.</p>
            <Link to={this.props.returnURL} className="btn btn-success lidtPlads">Back</Link>
          </div>
        </div>
        <div className="col-sm-4"></div>
      </div>
    )
  }
}
