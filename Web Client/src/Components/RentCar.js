import React, { Component } from "react";
import facade from "../apiFacade";
import { Link } from 'react-router-dom'
import getFormattedDate from "../Components/DateFormatter";
import Calendar from "react-calendar";


class RentCar extends Component {
    constructor(props) {
      super(props);
      this.state = { location: props.locValue, categori: props.catValue, startDate: props.startDate, endDate: props.endDate }
  
      this.onChangeStart = this.onChangeStart.bind(this);
      this.onChangeEnd = this.onChangeEnd.bind(this);
      this.handleChangeLocation = this.handleChangeLocation.bind(this);
      this.handleChangeCategori = this.handleChangeCategori.bind(this);
    }
  
  
    componentWillReceiveProps(props) {
      this.setState({ startDate: props.startDate, endDate: props.endDate, location: props.locValue, categori: props.catValue });
    }
  
    onChangeStart(date) {
      this.props.setDateStart(date)
      this.setState({ startDate: date },
        function () {
          let s = getFormattedDate(this.state.startDate);
          let e = getFormattedDate(this.state.endDate);
          if (s === e) {
            this.props.setDateURL("");
          } else {
            this.props.setDateURL("?start=" + s + "&end=" + e)
          }
        }
      );
    }
    onChangeEnd(date) {
      this.props.setDateEnd(date)
      this.setState({ endDate: date },
        function () {
          let s = getFormattedDate(this.state.startDate);
          let e = getFormattedDate(this.state.endDate);
          if (s === e) {
            this.props.setDateURL("");
          } else {
            this.props.setDateURL("?start=" + s + "&end=" + e)
          }
        }
      );
    }
  
    handleChangeLocation(event) {
      this.setState({ location: event.target.value });
      this.props.setLocValue(event.target.value);
      if (event.target.value === "All") {
        this.props.setLocationURL("");
      } else {
        this.props.setLocationURL("?location=" + event.target.value);
      }
    }
    handleChangeCategori(event) {
      this.setState({ categori: event.target.value });
      this.props.setCatValue(event.target.value);
      if (event.target.value === "All") {
        this.props.setCategoryURL("");
      } else {
        this.props.setCategoryURL("?category=" + event.target.value);
      }
    }
  
    render() {
      return (
        <div className="panel-body transparent">
          <br />
          <div className="row">
            <div className="col-sm-3"></div>
            <div className="col-sm-6 alert alert-info text-center"> <h1>Welcome to CarMondo!</h1></div>
            <div className="col-sm-3"></div>
          </div>
          <br />
          <div className="row">
            <div className="col-md-3">
            </div>
            <div className="col-md-2 text-center">
  
              <h3><span className="label label-default">Category</span></h3>
              <br />
              <div className="btn-group customBredde">
                <select className="form-control" value={this.state.categori} onChange={this.handleChangeCategori}>
                  <option value="All">All</option>
                  <option value="Mini">Mini</option>
                  <option value="Mini Elite">Mini Elite</option>
                  <option value="Economy">Economy</option>
                  <option value="Economy Elite">Economy Elite</option>
                  <option value="Compact">Compact</option>
                  <option value="Compact Elite">Compact Elite</option>
                  <option value="Intermediate">Intermediate</option>
                  <option value="Intermediate Elite">Intermediate Elite</option>
                  <option value="Standard">Standard</option>
                  <option value="Standard Elite">Standard Elite</option>
                  <option value="Fullsize">Fullsize</option>
                  <option value="Fullsize Elite">Fullsize Elite</option>
                  <option value="Premium">Premium</option>
                  <option value="Premium Elite">Premium Elite</option>
                  <option value="Luxury">Luxury</option>
                  <option value="Luxury Elite">Luxury Elite</option>
                  <option value="Oversize">Oversize</option>
                  <option value="Special">Special</option>
                </select>
                <Link onClick={this.setBookingAvailable} to="/showcatcars" className="btn btn-info custBredde">Show</Link>
              </div>
              <br />
            </div>
  
            <div className="col-md-2 text-center">
  
              <h3><span className="label label-default">Location</span></h3>
              <br />
              <div className="btn-group customBredde">
                <select className="form-control" value={this.state.location} onChange={this.handleChangeLocation}>
                  <option value="All">Alle</option>
                  <option value="Cph (Copenhagen Airport)">Cph (Copenhagen Airport)</option>
                  <option value="Billund Lufthavn">Billund Lufthavn</option>
                  <option value="Aalborg Lufthavn">Aalborg Lufthavn</option>
                  <option value="Copenhagen City">Copenhagen City</option>
                  <option value="Aarhus City">Aarhus City</option>
                  <option value="Odense">Odense</option>
                  <option value="Herning">Herning</option>
                  <option value="Roskilde">Roskilde</option>
                  <option value="Esbjerg">Esbjerg</option>
                  <option value="Naestved">Næstved</option>
                </select>
                <Link onClick={this.setBookingAvailable} to="/showloccars" className="btn btn-info btn-block custBredde">Show</Link>
              </div>
            </div>
  
            <div className="col-md-2 ">
              <div className="">
                <h3><span className="label label-default minMargin" >Date</span></h3>
                Start:
              <div className="btn-group dropright">
                  <button type="button" className="btn btn-secondary dropdown-toggle btn-block" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    {getFormattedDate(this.state.startDate)}
                  </button>
                  <div className="dropdown-menu">
                    <Calendar className="dropdown-item" href="#" onClickDay={this.onChangeStart} value={this.state.startDate} />
                  </div>
  
                </div>
  
              </div>
              <div>
                <a className="myMargin">
                  Slut:
              </a>
                <div className="btn-group dropright">
                  <button type="button" className="btn btn-secondary dropdown-toggle btn-block" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    {getFormattedDate(this.state.endDate)}
                  </button>
                  <div className="dropdown-menu">
                    <Calendar className="dropdown-item" href="#" onClickDay={this.onChangeEnd} value={this.state.endDate} />
                  </div>
                  <br />
                </div>
                <Link onClick={this.setBookingAvailable} to="/showdatecars" className="btn btn-info showsize">Show</Link>
  
              </div>
            </div>
  
          </div>
          <br /><br />
  
          <div className="row">
            <div className="col-md-5"> </div>
            <div className="col-md-2">
              <form>
                <div className="form group">
  
                  
                  {(getFormattedDate(this.state.startDate) === getFormattedDate(this.state.endDate)) &&    
                        <button className="btn btn-success btn-lg btn-block" data-toggle="popover" data-trigger="focus" data-placement="right" data-content="Only available with two different dates">Show Combination</button>  
                        
                  }
  
                  {(getFormattedDate(this.state.startDate) !== getFormattedDate(this.state.endDate)) &&
                    <Link onClick={this.setBookingAvailable} to="/showcombicars" className="btn btn-success btn-lg btn-block">Show Combination</Link>
                  }
  
  
                </div>
  
              </form>
            </div>
            <div className="col-md-5">  </div>
  
          </div>
  
          <br /><br />
  
          <div className="row">
            <div className="col-md-5"> </div>
            <div className="col-md-2">
              <form>
                <div className="form group">
                  <Link onClick={this.setBookingAvailable} to="/showallcars" className="btn btn-success btn-lg btn-block">Show All Cars</Link>
                </div>
              </form>
            </div>
  
  
            <div className="col-md-5"> </div>
          </div>
          <br /><br /><br /><br /><br /><br /><br />
  
        </div>
  
      )
    }
  }

  export default RentCar;