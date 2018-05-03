import React, { Component } from "react"
import {
  HashRouter as Router,
  Route,
  Switch,
  NavLink,
  Link
} from 'react-router-dom'
import facade from "./apiFacade";
import jwt_decode from 'jwt-decode';

const NoMatch = () => (
  <h1> No Match </h1>
)


const Home = () => (
  <div>
    Welcome to CarMondo
  </div>
)

class RentCar extends Component {
  constructor(props) {
    super(props);
    this.state = { location: "Alle", categori: "Alle"}

    this.handleChangeLocation = this.handleChangeLocation.bind(this);
    this.handleChangeCategori = this.handleChangeCategori.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }


  handleChangeLocation(event) {
    this.setState({location: event.target.value});
    if (event.target.value == "All") {
      this.props.setURL("");
    } else {  
      this.props.setURL("?location="+event.target.value);
    }
  }
  handleChangeCategori(event) {
    this.setState({categori: event.target.value});
    if (event.target.value == "All") {
      this.props.setURL("");
    } else {  
          this.props.setURL("?category="+event.target.value);
    }      
  }

  handleSubmit(event) {
    alert('submitted: ' + 'loca: ' + this.state.location + ", kate: " +this.state.categori);
    event.preventDefault();
  }

  render() {
    return (
      <div>

        <div class="row">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 well well-lg"> <h2>Welcome to CarMondo</h2></div>
          <div class="col-sm-4"></div>
        </div>

        <div class="row">
          <div class="col-sm-5"> </div>
          <div class="col-sm-3">
            <form>
              <div class="form group">
               <Link to="/showcars" class="btn btn-success btn-lg">Show All Cars</Link>
              </div>
            </form>
          </div>
          <div class="col-sm-4"> </div>
        </div>

        <div class="form-group">
          &nbsp;
        </div>

        <div class="row">
          <div class="col-sm-4"> </div>
          <div class="col-sm-4">
            <form class="form-inline">
              <div class="form group">
                <label> Kategori: </label>
                <select class="form-control" value={this.state.categori} onChange={this.handleChangeCategori}>
                  <option selected value="All">Alle</option>
                  <option value="Mini">Mini</option>
                  <option value="Economy">Economy</option>
                  <option value="Fullsize">Fullsize</option>
                </select>
                <Link to="/showcars" class="btn btn-success">Show All Cars</Link>
              </div>
            </form>
          </div>
          <div class="col-sm-4"> </div>
        </div>

        <div class="form-group">
          &nbsp;
        </div>

        <div class="row">
          <div class="col-sm-4"> </div>
          <div class="col-sm-4">
            <form class="form-inline">
              <div class="form group">
                <label> Lokation: </label>
                <select class="form-control" value={this.state.location} onChange={this.handleChangeLocation}>
                  <option selected value="All">Alle</option>
                  <option value="Cph Airport">CPH Airport</option>
                  <option value="Aarhus City">Aarhus City</option>
                  <option value="Naestved">Næstved</option>
                </select>
                <Link to="/showcars" class="btn btn-success">Show All Cars</Link>
              </div>
            </form>
          </div>
          <div class="col-sm-4"> </div>
        </div>

      </div>
    )
  }
}

class ShowCars extends Component {
  constructor(props) {
    super(props);
    this.state = { fetchURL: props.fetchURL, dataFromServer: { cars: []} };
  }
  componentDidMount() {
    facade.fetchCars(this.state.fetchURL).then(res => this.setState({ dataFromServer: res }));
  }
  render() {
    var cars = this.state.dataFromServer.cars;
    var linkTable = cars.map((car) => {
      return (
        <tr  scope="row" key={car.regno}>
          <td>{car.make}</td>
          <td>{car.model}</td>         
          <td>{car.location}</td>
          <td>{car.priceperday}</td>
          <td><Link to={`details/${car.regno}`} class="btn btn-success">Show Details</Link></td>
          <td><Link to="/showcars" class="btn btn-success">Book</Link></td>
          {/* <td><Link to={`booking/${car.regno}`} class="btn btn-success">Book</Link></td> */}
        </tr>
      )
    });

    return (
      <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
          <div class="well well-lg"> <h2> List of Cars</h2> </div>
          <table class="table" key="tableList">
            <tbody>
              <tr>               
                <th scope="col">Make</th>
                <th scope="col">Model</th>
                <th scope="col">Location</th>
                <th scope="col">PricePerDay</th>
                <th scope="col">Details</th>
                <th scope="col">Booking</th>
              </tr>
              {linkTable}
            </tbody>
          </table>
          <br />

          <Link to="/" class="btn btn-success">Back</Link>

        </div>
        <div class="col-sm-2"></div>
      </div>

    )
  }
}


class Header extends Component {
  constructor(props) {
    super(props);

  }
  render() {
    return (
      <div>
        <Router>
          <nav class="navbar navbar-inverse">
            <div class="container-fluid">
              <div class="navbar-header">
                <a class="navbar-brand">CarMondo</a>
              </div>
              <ul class="nav navbar-nav">
                <li><NavLink exact to="/">Rent Car</NavLink></li>
                {/* {this.state.userroles === "user" && <li><NavLink to="/userdata">UserData</NavLink></li>}
                {this.state.userroles === "admin" && <li><NavLink to="/admindata">AdminData</NavLink></li>} */}
                {/* <li><NavLink to="/swapi">Swapi</NavLink></li> */}
              </ul>
              <ul class="nav navbar-nav navbar-right">
                {/* <li><button onClick={this.props.logout} class='btn btn-link'> <span class="glyphicon glyphicon-log-out"></span> Logout</button></li> */}
              </ul>
            </div>
          </nav>
        </Router>
        <div>
          <span>
            {/* <div class="well well-sm"><h4> Logged in as : {this.state.username}</h4></div> */}
          </span>
        </div>
      </div>
    )
  }
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { loggedIn: false, fetchURL: "" }
  }
  setURL = (url) => {
    this.setState({ fetchURL: url });
    console.log(url);
  }



  render() {
    return (
      <div>
        <div>
          <Header />
          
          <Router>
            <Switch>
              <Route exact path="/" render={() => <RentCar setURL={this.setURL}/>} />
              <Route path="/showcars" render={() => <ShowCars fetchURL={this.state.fetchURL} />} />
              
              {/* <Route path="/swapi" render={() => <FetchSwapi />} /> */}
              {/* <Route path="/userdata" render={() => <UserData />} />
                <Route path="/admindata" render={() => <UserData />} /> */}
              <Route component={NoMatch} />
            </Switch>
          </Router>
        </div>

        <div class="row">
          <br />
          <div class="col-md-5"></div>
          {/* {this.state.loginError &&
            <span><div class="col-md-2 alert alert-danger"> {this.state.loginError} </div></span>
              } */}
          <div class="col-md-5"></div>
        </div>
      </div>
    )
  }
}

export default App;
