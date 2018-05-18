import React, { Component } from "react";
import facade from "../apiFacade";
import { Link } from 'react-router-dom'

export default class CarDetails extends Component {
  constructor(props) {
    super(props);
    this.state = { fetchURL: props.fetchURL, dataFromServer: {}, regno: props.match.params.regno, bookingBoolean: props.bookingBoolean };
  }
  componentDidMount() {
    facade.fetchSingleCar(this.state.regno).then(res => this.setState({ dataFromServer: res }));
  }
  render() {
    var car = this.state.dataFromServer;
    return (
      <div className="">
        <div className="row">
          <div className="col-sm-2"></div>
          <div className="col-sm-8">
            <div className="text-center"> <h3> Car Details</h3> </div>
            <table className="table table-sm table-dark nonTransparent1 rounded" key="tableList">
              <tbody>
                <tr>
                  <th scope="col">Category</th>
                  <th scope="col">Make</th>
                  <th scope="col">Model</th>
                  <th scope="col">Year</th>
                  <th scope="col">Regno</th>
                  <th scope="col">Seats</th>
                  <th scope="col">Doors</th>
                  <th scope="col">Gear</th>
                  <th scope="col">Aircondition</th>
                  <th scope="col">Location</th>
                  <th scope="col">PricePerDay</th>
                  {this.state.bookingBoolean === true &&
                    <th scope="col">Booking</th>
                  }
                </tr>
                <tr key={car.regno}>
                  <td>{car.category}</td>
                  <td>{car.model}</td>
                  <td>{car.make}</td>
                  <td>{car.year}</td>
                  <td>{car.regno}</td>
                  <td>{car.seats}</td>
                  <td>{car.doors}</td>
                  <td>{car.gear}</td>
                  <td>{"" + car.aircondition}</td>
                  <td>{car.location}</td>
                  <td>{car.priceperday}</td>
                  {this.state.bookingBoolean === true &&
                    <td><Link to={`../clientdata/${car.regno}`} className="btn btn-success">Book</Link></td>
                  }
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div className="row">
          <div className="col-md-3"></div>
          <div className="col-md-6 nonTransparent rounded border border-dark text-center">
            <div className="flyvVenstreLogo ">
              <img src={car.logo} width="150px" height="150px" alt="" />
              <p className="textColor">{car.company}</p>
            </div>
            <div className="flyvVenstreMerePlads">
              <img className="" src={car.picture} width="225px" height="150px" alt="" />
            </div>
            <div className="flyvHøjreLogo ">
              <img src={car.logo} width="150px" height="150px" alt="" />
              <p className="textColor">{car.company}</p>
            </div>
          </div>
          <div className="col-md-5"></div>
        </div>
        <div className="row">
          <div className="col-sm-3"></div>
          <div className="col-sm-6">
            <br />
            <Link to={this.props.returnURL} className="btn btn-success">Back</Link>
          </div>
          <div className="col-sm-3"></div>
        </div>
      </div>
    )
  }
}
