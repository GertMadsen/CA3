import React, { Component } from "react"
import {
  HashRouter as Router,
  Route,
  Switch,
  NavLink
} from 'react-router-dom'
import facade from "./apiFacade";
import jwt_decode from 'jwt-decode';

const NoMatch = () => (
  <h1> No Match </h1>
)

class LogIn extends Component {

  constructor(props) {
    super(props);
    this.state = { username: "", password: "" }
  }
  login = (evt) => {
    evt.preventDefault();
    this.props.login(this.state.username, this.state.password);
  }
  onChange = (evt) => {
    this.setState({ [evt.target.id]: evt.target.value })
  }

  render() {
    return (
      <div class="row">
        <div class="col-md-5"></div>
        <div class="col-md-2">
          <h3><span class="label label-primary">Login</span></h3>
          <form onSubmit={this.login} onChange={this.onChange} >
            <div class="input-group">
              <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input class="form-control" placeholder="User Name" id="username" />
              </div>
              <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input class="form-control" placeholder="Password" id="password" />
              </div>
              <button class='btn btn-success btn-block'>Login</button>
            </div>

          </form>
        </div>
        <div class="col-md-5"></div>
      </div>
    )
  }
}

class FetchSwapi extends Component {
  constructor(props) {
    super(props);
    var person = facade.fetchPerson;
    this.state = { pers: person };
  }

  componentDidMount() {
    facade.fetchPerson().then(res => this.setState({ pers: res }));
  }

  render() {
    return (
      <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
          <div class="well well-sm"> <h4> Name:  {this.state.pers.name}</h4></div>
          <div class="well well-sm">  <h4> Height: {this.state.pers.height} </h4></div>
          <div class="well well-sm">  <h4> Weight : {this.state.pers.mass} </h4></div>
          <div class="well well-sm">  <h4> Gender : {this.state.pers.gender} </h4></div>
        </div>
        <div class="col-sm-4"></div>
      </div>
    )
  }
}

const Home = () => (
  <div class="row">
    <div class="col-sm-4"></div>
    <div class="col-sm-4 well well-lg"> <h2>Welcome to the Seed</h2></div>
    <div class="col-sm-4"></div>
  </div>
)

class UserData extends Component {
  constructor(props) {
    super(props);
    var userToken = facade.getToken();
    var decoded = jwt_decode(userToken);
    var userRoles = decoded.roles;
    this.state = { dataFromServer: "Fetching!!", userroles: userRoles };
  }
  componentDidMount() {
    facade.fetchUserData(this.state.userroles).then(res => this.setState({ dataFromServer: res }));
  }
  render() {
    return (
      <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
          <div class="well well-lg"> <h2> Data Received from server </h2> </div>
          <div class="well well-lg">  <h3> {this.state.dataFromServer} </h3> </div>
        </div>
        <div class="col-sm-2"></div>
      </div>

    )
  }
}

class Header extends Component {
  constructor(props) {
    super(props);
    var userToken = facade.getToken();
    var decoded = jwt_decode(userToken);
    var userName = decoded.sub;
    var userRoles = decoded.roles;
    this.state = { username: userName, userroles: userRoles };
  }
  render() {
    return (
      <div>
        <Router>
          <nav class="navbar navbar-inverse">
            <div class="container-fluid">
              <div class="navbar-header">
                <a class="navbar-brand">CA3 Seed</a>
              </div>
              <ul class="nav navbar-nav">
                <li><NavLink exact to="/">Home</NavLink></li>
                {this.state.userroles === "user" && <li><NavLink to="/userdata">UserData</NavLink></li>}
                {this.state.userroles === "admin" && <li><NavLink to="/admindata">AdminData</NavLink></li>}
                <li><NavLink to="/swapi">Swapi</NavLink></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><button onClick={this.props.logout} class='btn btn-link'> <span class="glyphicon glyphicon-log-out"></span> Logout</button></li>
              </ul>
            </div>
          </nav>
        </Router>
        <div>
          <span>
            <div class="well well-sm"><h4> Logged in as : {this.state.username}</h4></div>
          </span>
        </div>
      </div>
    )
  }
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { loggedIn: false }
  }
  logout = () => {
    facade.logout();
    this.setState({ loggedIn: false });
  }
  login = (user, pass) => {
    this.setState({ loginError: "" })
    facade.login(user, pass)
      .then(res => this.setState({ loggedIn: true }))
      .catch(error => {
        this.setState({ loginError: "User or Password Incorrect" })
      })
  }
  render() {
    return (
      <div>
        {!this.state.loggedIn ? (<LogIn login={this.login} />) :
          (<div>
            <Header logout={this.logout} />

            <Router>
              <Switch>
                <Route exact path="/" render={() => <Home />} />
                <Route path="/swapi" render={() => <FetchSwapi />} />
                <Route path="/userdata" render={() => <UserData />} />
                <Route path="/admindata" render={() => <UserData />} />
                <Route component={NoMatch} />
              </Switch>
            </Router>
          </div>)}

        <div class="row">
          <div class="col-md-5"></div>

          {this.state.loginError &&
            <span><div class="col-md-2 alert alert-danger"> {this.state.loginError} </div></span>
              }
            <div class="col-md-5"></div>
          </div>
      </div>
    )
  }
}

export default App;
