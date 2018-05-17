import React, { Component } from "react"
import { HashRouter as Router, Route, Switch, Link } from 'react-router-dom'
import facade from "./apiFacade";
import Calendar from "react-calendar";
import getFormattedDate from "./Components/DateFormatter";
import Header from "./Components/Header";
import ClientData from "./Components/ClientData";
import RentCar from "./Components/RentCar";
import ShowCars from "./Components/ShowCars";
import CarDetails from "./Components/CarDetails";
import BookingInfo from "./Components/BookingInfo";
import Confirmation from "./Components/Confirmation";
import NoMatch from "./Components/NoMatch";


class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false, locationURL: "", categoryURL: "", locValue: "", catValue: "", returnURL: "", dateURL: "", startDate: new Date(), endDate: new Date(),
      user: { firstname: "", lastname: "", email: "" },
      car: { reservations: [] }, bookingBoolean: false
    }
  }

  setBookingAvailable = (boolean) => {
    this.setState({bookingBoolean: boolean})
  }

  setDateStart = (date) => {
    this.setState({ startDate: date })
  }
  setDateEnd = (date) => {
    this.setState({ endDate: date })
  }

  setDateURL = (url) => {
    this.setState({ dateURL: url });
  }

  setLocationURL = (url) => {
    this.setState({ locationURL: url });
  }
  setCategoryURL = (url) => {
    this.setState({ categoryURL: url });
  }
  setLocValue = (value) => {
    this.setState({ locValue: value });
  }
  setCatValue = (value) => {
    this.setState({ catValue: value });
  }
  setReturnURL = (url) => {
    this.setState({ returnURL: url });
  }
  setCar = (car) => {
    this.setState({ car: car });
  }

  setUserFname = (value) => 
    this.setState(prevState => ({
      user: {
        ...prevState.user,
        firstname: value
      }
    }))

  setUserLname = (value) =>
    this.setState(prevState => ({
      user: {
        ...prevState.user,
        lastname: value
      }

    }))
  setUserEmail = (value) =>
    this.setState(prevState => ({
      user: {
        ...prevState.user,
        email: value
      }
    }))

  render() {
    var combiURL = "";
    var locStr = this.state.locationURL;
    var locURL = "&" + locStr.substring(1, locStr.length);
    var catStr = this.state.categoryURL;
    var catURL = "&" + catStr.substring(1, catStr.length);
    var dateDummy = "?start=" + getFormattedDate(this.state.startDate) + "&end=" + getFormattedDate(this.state.endDate);

    if (this.state.dateURL !== "") {
      combiURL += this.state.dateURL;
      if (locStr !== "") {
        combiURL += locURL;
      }
      if (catStr !== "") {
        combiURL += catURL;
      }
    } else {
      if (locStr !== "") {
        combiURL += dateDummy + locURL;
        if (catStr !== "") {
          combiURL += catURL;
        }
      } else {
        if (catStr !== "") {
          combiURL += dateDummy + catURL;
        }
      }
    }

    return (
      <div>
        <div>

          <Header />

          <div className="p">
            <Router>
              <Switch>
                <Route exact path="/" render={(props) => <RentCar catValue={this.state.catValue} setCatValue={this.setCatValue} locValue={this.state.locValue} setLocValue={this.setLocValue} setCategoryURL={this.setCategoryURL} setLocationURL={this.setLocationURL} setDateEnd={this.setDateEnd} setDateStart={this.setDateStart} startDate={this.state.startDate} endDate={this.state.endDate} setDateURL={this.setDateURL}
                  {...props} />} />
                <Route path="/showallcars" render={(props) => <ShowCars setBookingBoolean={this.setBookingAvailable} bookingAvailable={false} setReturnURL={this.setReturnURL} fetchURL="" {...props} />} />
                <Route path="/showloccars" render={(props) => <ShowCars setBookingBoolean={this.setBookingAvailable} bookingAvailable={false} setReturnURL={this.setReturnURL} fetchURL={this.state.locationURL} {...props} />} />
                <Route path="/showdatecars" render={(props) => <ShowCars setBookingBoolean={this.setBookingAvailable} bookingAvailable={true} setReturnURL={this.setReturnURL} fetchURL={this.state.dateURL} {...props} />} />
                <Route path="/showcatcars" render={(props) => <ShowCars setBookingBoolean={this.setBookingAvailable} bookingAvailable={false} setReturnURL={this.setReturnURL} fetchURL={this.state.categoryURL} {...props} />} />
                <Route path="/showcombicars" render={(props) => <ShowCars setBookingBoolean={this.setBookingAvailable} bookingAvailable={true} setReturnURL={this.setReturnURL} fetchURL={combiURL} {...props} />} />                
                <Route path="/details/:regno" render={(props) => <CarDetails bookingBoolean={this.state.bookingBoolean} setReturnURL={this.setReturnURL} returnURL={this.state.returnURL} {...props} />} />
                <Route path="/bookinginfo/:regno" render={(props) => <BookingInfo returnURL={this.state.returnURL} firstname={this.state.user.firstname} lastname={this.state.user.lastname} email={this.state.user.email} start={this.state.startDate} end={this.state.endDate} {...props} />} />
                <Route path="/clientdata/:regno" render={(props) => <ClientData setCar={this.setCar} returnURL={this.state.returnURL}
                  firstname={this.state.user.firstname} lastname={this.state.user.lastname} email={this.state.user.email}
                  setUserFname={this.setUserFname} setUserLname={this.setUserLname} setUserEmail={this.setUserEmail}
                  {...props} />} />
                <Route path="/confirmation/" render={(props) => <Confirmation returnURL={this.state.returnURL} firstname={this.state.user.firstname} lastname={this.state.user.lastname} email={this.state.user.email} start={this.state.startDate} end={this.state.endDate} car={this.state.car} {...props} />} />
                <Route component={NoMatch} />
              </Switch>
            </Router>
          </div>
        </div>
      </div>
    )
  }
}

export default App;
