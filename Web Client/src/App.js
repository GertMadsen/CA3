import React, { Component } from "react"
import {
  HashRouter as Router,
  Route,
  Switch,
  Link
} from 'react-router-dom'
import facade from "./apiFacade";
import Calendar from "react-calendar";


const NoMatch = () => (
  <h1> No Match </h1>
)

function getFormattedDate(date) {
  var year = date.getFullYear();

  var month = (1 + date.getMonth()).toString();
  month = month.length > 1 ? month : '0' + month;

  var day = date.getDate().toString();
  day = day.length > 1 ? day : '0' + day;

  return day + '-' + month + '-' + year;
}

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
      function (){
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
      function (){
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

            <h3><span className="label label-default">Kategori</span></h3>
            <br />
            <div className="btn-group customBredde">
              <select className="form-control" value={this.state.categori} onChange={this.handleChangeCategori}>
                <option value="All">Alle</option>
                <option value="Mini">Mini</option>
                <option value="Economy">Economy</option>
                <option value="Fullsize">Fullsize</option>
              </select>
              <Link onClick={this.setBookingAvailable} to="/showcatcars" className="btn btn-info custBredde">Show</Link>
            </div>
            <br />
          </div>

          <div className="col-md-2 text-center">

            <h3><span className="label label-default">Lokation</span></h3>
            <br />
            <div className="btn-group customBredde">
              <select className="form-control" value={this.state.location} onChange={this.handleChangeLocation}>
                <option value="All">Alle</option>
                <option value="Cph Airport">CPH Airport</option>
                <option value="Aarhus City">Aarhus City</option>
                <option value="Naestved">Næstved</option>
              </select>
              <Link onClick={this.setBookingAvailable} to="/showloccars" className="btn btn-info btn-block custBredde">Show</Link>
            </div>
          </div>

          <div className="col-md-2 ">
            <div className="">
              <h3><span className="label label-default minMargin" >Dato</span></h3>
              Start:
            <div className="btn-group dropright">
                <button type="button" className="btn btn-secondary dropdown-toggle btn-block" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  {getFormattedDate(this.state.startDate)}
                </button>
                <div className="dropdown-menu">
                  <Calendar className="dropdown-item" href="#" onChange={this.onChangeStart} value={this.state.startDate} />
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
                  <Calendar className="dropdown-item" href="#" onChange={this.onChangeEnd} value={this.state.endDate} />
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
                <Link onClick={this.setBookingAvailable} to="/showcombicars" className="btn btn-success btn-lg btn-block">Show Selected Cars</Link>
              </div>
            </form>
          </div>
          <div className="col-md-5"> </div>
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



class ShowCars extends Component {
  constructor(props) {
    super(props);
    this.state = { fetchURL: props.fetchURL, dataFromServer: { cars: [] }, available: props.available };
    this.props.setReturnURL(props.match.url);
  }
  componentDidMount() {
    facade.fetchCars(this.state.fetchURL).then(res => this.setState({ dataFromServer: res }));
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
          {this.state.available === true &&
            <td><Link to={`clientdata/${car.regno}`} className="btn btn-success">Book</Link></td>
          }

        </tr>

      )
    });
    return (
      <div className="row">
        <div className="col-sm-2"></div>
        <div className="col-sm-8">
          <div> <h3> List of Cars</h3> </div>
          <table className="table table-dark table-sm table-hover" key="tableList">
            <tbody>
              <tr>
                <th scope="col">Make</th>
                <th scope="col">Model</th>
                <th scope="col">Category</th>
                <th scope="col">Location</th>
                <th scope="col">PricePerDay</th>
                <th scope="col">Details</th>
                {this.state.available === true &&
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

class CarDetails extends Component {
  constructor(props) {
    super(props);
    this.state = { fetchURL: props.fetchURL, dataFromServer: {}, regno: props.match.params.regno, available: props.available };
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
                {this.state.available === true &&
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
                {this.state.available === true &&
                 <td><Link to={`../clientdata/${car.regno}`} className="btn btn-success">Book</Link></td>
                 }
              </tr>
            </tbody>
          </table>
          </div>
          <div className="col-sm-2"></div>
          </div>
         
          <div className="row">
          <div className="col-md-3"></div>
            <div className="col-md-6 nonTransparent rounded border border-dark text-center">
             <div className="flyvVenstre lidtPlads">
               <img  src={car.logo} width="150px" height="150px" />
               <h2 className="textColor">{car.company}</h2>
             </div>

            <img className="lidtPlads" src={car.picture} width="40%" height="80%"/>
 
            <div className="flyvHøjre lidtPlads">
              <img  src={car.logo} width="150px" height="150px" />
              <h2 className="textColor">{car.company}</h2>
            </div>
          </div>
          <div className="col-md-3"></div>
          </div>
        
          <div className="row">
          <div className="col-sm-3"></div>
          <div className="col-sm-6">
          <br/>
           <Link to={this.props.returnURL} className="btn btn-success">Back</Link>
          </div>
          <div className="col-sm-3"></div>
         </div>
        
      </div>

    )
  }
}

class BookingInfo extends Component {
  constructor(props) {
    super(props);
    this.state = { fetchURL: props.fetchURL, dataFromServer: {}, 
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
        <br/>
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
          <Link to={this.props.returnURL} className="btn btn-success flyvVenstre lidtPlads">Back</Link>
          <Link to="/confirmation" className="btn btn-success flyvHøjre lidtPlads">Confirm Booking</Link>

          <br /><br />

        </div>
        </div>
        <div className="col-sm-4"></div>
      </div>

    )
  }
}


class Confirmation extends Component {
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
          <p> located at <b>{this.state.car.location}</b> has been completed.</p>

          <Link to={this.props.returnURL} className="btn btn-success lidtPlads">Back</Link>

        </div>
        </div>
        <div className="col-sm-4"></div>
      </div>

    )
  }
}




class Header extends Component {

  render() {
    return (
      <div>
        <Router>
          <nav className="navbar navbar-dark bg-dark">
            <a className="navbar-brand" href="">CarMondo</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
              </ul>
            </div>
          </nav>
        </Router>
      </div>
    )




  }
}

class ClientData extends Component {

  constructor(props) {
    super(props);
    this.state = {
      firstname: props.firstname, lastname: props.lastname, email: props.email,
      regno: props.match.params.regno, dataFromServer: [], emptyState: "", errorMessage: ""
    }
    this.handleChangeFname = this.handleChangeFname.bind(this);
    this.handleChangeLname = this.handleChangeLname.bind(this);
    this.handleChangeEmail = this.handleChangeEmail.bind(this);
  }


  componentDidMount() {
    facade.fetchSingleCar(this.state.regno).then(res => this.setState({ dataFromServer: res }));
      this.resetInformation()
  }

  handleChangeFname(event) {
    this.setState({ firstname: event.target.value });
    this.props.setUserFname(event.target.value);
  }
  handleChangeLname(event) {
    this.setState({ lastname: event.target.value })
    this.props.setUserLname(event.target.value);
  }
  handleChangeEmail(event) {
    this.setState({ email: event.target.value })
    this.props.setUserEmail(event.target.value);
    this.props.setCar(this.state.dataFromServer);
  }

  resetInformation() {
    this.setState({ email: this.state.emptyState })
    this.setState({ firstname: this.state.emptyState })
    this.setState({ lastname: this.state.emptyState })
    this.setState({ message: this.state.emptyState })
    this.props.setUserEmail(this.state.emptyState);
    this.props.setUserFname(this.state.emptyState);
    this.props.setUserLname(this.state.emptyState);
  }

  errorHandling() {
    this.setState({ errorMessage: "All fields must be filled out" })
  }

  render() {
    var car = this.state.dataFromServer;

    return (

      <div className="row">

        <div className="col-sm-4"></div>
        <div className="col-sm-4">
        <br />
        <div className="nonTransparent text-center textColor rounded">
          <div className="well well-sm"> <h3> Customer Data</h3> </div>

          <form >
            <div className="form-group ">

              <label className="col-form-label">
                Firstname:
          <input
                  className="form-control"
                  name="firstname"
                  type="text"
                  placeholder="firstname"
                  onChange={this.handleChangeFname} />
              </label>
              <br />
              <label className="col-form-label">
                Lastname:
          <input
                  className="form-control"
                  name="lastname"
                  type="text"
                  placeholder="lastname"
                  onChange={this.handleChangeLname} />
              </label>
              <br />
              <label className="col-form-label">
                Email:
              <input
                  className="form-control"
                  name="email"
                  type="email"
                  placeholder="email"
                  onChange={this.handleChangeEmail} />
              </label>
            </div>
            All fields must be filled out to continue
          </form>

          <br />
          <Link to={this.props.returnURL} className="btn btn-success lidtPlads">Back</Link>
          {" "}
          {(this.state.firstname.length === 0 || this.state.lastname.length === 0 || this.state.email.length === 0) &&
            <button onClick={this.errorHandling.bind(this)} className="btn btn-success lidtPlads">Continue</button>
          }

          {(this.state.firstname.length > 0 && this.state.lastname.length > 0 && this.state.email.length > 0) &&
            <Link to={`/bookinginfo/${car.regno}`} className="btn btn-success">Continue</Link>
          }
          <div><h3 className="text-danger">{this.state.errorMessage}</h3></div>


        </div>
        </div>
        <div className="col-sm-4"></div>
        
      </div>

    )
  }

}



class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false, locationURL: "", categoryURL: "", locValue: "", catValue: "", returnURL: "", dateURL: "", startDate: new Date(), endDate: new Date(),
      user: { firstname: "", lastname: "", email: "" },
      car: { reservations: [] }

    }
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
    var dateDummy = "?start="+getFormattedDate(this.state.startDate)+"&end="+getFormattedDate(this.state.endDate);

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
        combiURL += dateDummy+locURL;
        if (catStr !== "") {
          combiURL += catURL;
        }
      } else {
        if (catStr !== "") {
          combiURL += dateDummy+catURL;
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
                <Route path="/showallcars" render={(props) => <ShowCars available={false} setReturnURL={this.setReturnURL} fetchURL="" {...props} />} />
                <Route path="/showloccars" render={(props) => <ShowCars available={false} setReturnURL={this.setReturnURL} fetchURL={this.state.locationURL} {...props} />} />
                <Route path="/showdatecars" render={(props) => <ShowCars available={true} setReturnURL={this.setReturnURL} fetchURL={this.state.dateURL} {...props} />} />
                <Route path="/showcatcars" render={(props) => <ShowCars available={false} setReturnURL={this.setReturnURL} fetchURL={this.state.categoryURL} {...props} />} />
                <Route path="/showcombicars" render={(props) => <ShowCars available={true} setReturnURL={this.setReturnURL} fetchURL={"/combined" + combiURL} {...props} />} />

                
                <Route path="/details/:regno" render={(props) => <CarDetails available={this.state.available} setReturnURL={this.setReturnURL} returnURL={this.state.returnURL} {...props} />} />
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
