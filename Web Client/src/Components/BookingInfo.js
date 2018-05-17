import React, { Component } from "react";
import { Link } from 'react-router-dom'
import facade from "../apiFacade";
import getFormattedDate from "../Components/DateFormatter";

export default class BookingInfo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fetchURL: props.fetchURL, dataFromServer: {},
      regno: props.match.params.regno,
      firstname: props.firstname, lastname: props.lastname, email: props.email,
      fromDate: props.start, toDate: props.end
    };
  }
  componentDidMount() {
    facade.fetchSingleCar(this.state.regno).then(res => this.setState({ dataFromServer: res }));
  }
  render() {
    var car = this.state.dataFromServer;
    return (
      <div className="row">
        <div className="col-sm-4"></div>
        <div className="col-sm-4">
          <br />
          <div className="nonTransparent text-center textColor rounded">
            <div className=""> <h3> Booking info</h3> </div>
            <img src={car.picture} width="50%" height="30%" alt="" />
            <br /><br />
            <p> <b>Car Info:</b> </p>
            <p>You want to rent a <b>{car.make} {car.model}</b> from the location <b>{car.location}</b> </p>
            <p>In the period from <b>{getFormattedDate(this.state.fromDate)}</b> to <b>{getFormattedDate(this.state.toDate)}</b>. </p>
            <p> <b>Customer Info:</b> </p>
            <p> Name: <b>{this.state.firstname} {this.state.lastname} </b></p>
            <p> Email: <b>{this.state.email} </b></p>
            <Link to={this.props.returnURL} className="btn btn-success flyvVenstre lidtPladsBund">Back</Link>
            <Link to="/confirmation" className="btn btn-success flyvHøjre lidtPladsBund">Confirm Booking</Link>
            <br /><br />
          </div>
        </div>
        <div className="col-sm-4"></div>
      </div>
    )
  }
}
