import React, { Component } from "react"
import facade from "./apiFacade";
import jwt_decode from 'jwt-decode';
import {
  HashRouter as Router,
  Route,
  Switch,
  NavLink
} from 'react-router-dom'


const hejsa = () => (
  <h1> Hejsa </h1>
)
const NoMatch = () => (
  <h1> No Match </h1>
)

const Fetch = () => (
  <h1> fetching.. </h1>
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

class LoggedIn extends Component {
  constructor(props) {
    super(props);
    var userToken = facade.getToken();
    var decoded = jwt_decode(userToken);
    var userName = decoded.sub;
    var userRoles = decoded.roles;
    this.state = { dataFromServer: "Fetching!!", username: userName, userroles: userRoles };
  }

  componentDidMount() {
    facade.fetchData().then(res => this.setState({ dataFromServer: res }));
  }

  render() {
    return (
      <h1> hej </h1>
    )
  }
}

class WelcomeMsg extends Component {
  constructor(props) {
    super(props);
    var userToken = facade.getToken();
    var decoded = jwt_decode(userToken);
    var userName = decoded.sub;
    var userRoles = decoded.roles;
    this.state = { dataFromServer: "Fetching!!", username: userName, userroles: userRoles };
  }

  componentDidMount() {
    facade.fetchData().then(res => this.setState({ dataFromServer: res }));
  }

  render() {
    return (
      <div>
        <div>

          <Router>
            <div>
              <ul className="header">
                <li><NavLink exact to="/">Home</NavLink></li>
                <li><NavLink to="/fetch">Fetch</NavLink></li>
                <li><NavLink to="/topics">Topics</NavLink></li>
              </ul>
            </div>
          </Router>

          <h2> Data Received from server </h2>
          <h3> {this.state.dataFromServer} </h3>
          <h3> Name: {this.state.username} - Roles: {this.state.userroles}</h3>
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
    facade.login(user, pass)
      .then(res => this.setState({ loggedIn: true }));
  }
  render() {
    return (
      <div>

        {!this.state.loggedIn ? (<LogIn login={this.login} />) :
          (<div>
            <WelcomeMsg />
            <button onClick={this.logout}>Logout</button>
          </div>)}

        <Router>
          <Switch>
            <Route exact path="/" render={() => <div></div>} />
            <Route path="/fetch" component={Fetch} />
            <Route component={NoMatch} />
          </Switch>
        </Router>
      </div>
    )
  }
}

export default App;
