import React, { Component } from "react";
import facade from "../apiFacade";
import { Link } from 'react-router-dom'

class ShowCars extends Component {
  constructor(props) {
    super(props);
    this.state = { fetchURL: props.fetchURL, dataFromServer: { cars: [] }, bookingAvailable: props.bookingAvailable };
    this.props.setReturnURL(props.match.url);
  }
  componentDidMount() {
    facade.fetchCars(this.state.fetchURL).then(res => this.setState({ dataFromServer: res }));
    if(this.state.fetchURL.search("start")>0){
      this.props.setBookingBoolean(this.state.bookingAvailable);
    }else{
      this.props.setBookingBoolean(false);
    }
  }
  render() {
    var cars = this.state.dataFromServer.cars;
    var linkTable = cars.map((car) => {
      return (

        <tr key={car.regno} className="nonTransparent">
          <td>{car.make}</td>
          <td>{car.model}</td>
          <td>{car.category}</td>
          <td>{car.location}</td>
          <td>{car.priceperday}</td>
          <td><Link to={`details/${car.regno}`} className="btn btn-success">Show Details</Link></td>
          {this.state.fetchURL.search("start")>0 &&
            <td><Link to={`clientdata/${car.regno}`} className="btn btn-success">Book</Link></td>
          }

        </tr>

      )
    });
    return (
      <div className="row">
        <div className="col-sm-2"></div>
        <div className="col-sm-8">
          <div className="alert alert-info text-center overskriftSize1"> <h3> List of Cars</h3> </div>
          <table className="table table-dark table-sm table-hover" key="tableList">
            <tbody>
              <tr>
                <th scope="col">Make</th>
                <th scope="col">Model</th>
                <th scope="col">Category</th>
                <th scope="col">Location</th>
                <th scope="col">PricePerDay</th>
                <th scope="col">Details</th>
                {this.state.fetchURL.search("start")>0 &&
                  <th scope="col">Booking</th>
                }

              </tr>
              {linkTable}
            </tbody>
          </table>
          <br />

          <Link to="/" className="btn btn-success">Back</Link>

        </div>
        <div className="col-sm-2"></div>
      </div>

    )
  }
}

export default ShowCars;