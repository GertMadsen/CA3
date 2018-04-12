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
      <div>
        <h2>Login</h2>
        <form onSubmit={this.login} onChange={this.onChange} >
          <input placeholder="User Name" id="username" />
          <input placeholder="Password" id="password" />
          <button>Login</button>
        </form>
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
      <div>
        <h2> Name: {this.state.pers.name} </h2>
        <h3> Height: {this.state.pers.height} </h3>
        <h3> Weight : {this.state.pers.mass} </h3>
        <h3> Gender : {this.state.pers.gender} </h3>
      </div>
    )
  }
}

const Home = () => (
  <div>
    <h2>Welcome.</h2>
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
      <div>
        <div>
          <h2> Data Received from server </h2>
          <h3> {this.state.dataFromServer} </h3>
        </div>
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
        <div>
          <Router>
            <div>
              <ul className="header">
                <li><NavLink exact to="/">Home</NavLink></li>
                {this.state.userroles === "user" && <li><NavLink to="/userdata">UserData</NavLink></li>}
                {this.state.userroles === "admin" && <li><NavLink to="/admindata">AdminData</NavLink></li>}
                <li><NavLink to="/swapi">Swapi</NavLink></li>
              </ul>
            </div>
          </Router>
          <h3> Logged in as : {this.state.username}</h3>
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
            <Header />

            <Router>
              <Switch>
                <Route exact path="/" render={() => <Home />} />
                <Route path="/swapi" render={() => <FetchSwapi />} />
                <Route path="/userdata" render={() => <UserData />} />
                <Route path="/admindata" render={() => <UserData />} />
                <Route component={NoMatch} />
              </Switch>
            </Router>
            <button onClick={this.logout}>Logout</button>

          </div>)}

        <h3>{this.state.loginError} </h3>

      </div>
    )
  }
}

export default App;
