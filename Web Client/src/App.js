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

const FunFact = () => (
  <h1> Not Funny </h1>
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

class Fetching extends Component {
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
                <li><NavLink to="/fun">Fun Facts</NavLink></li>
              </ul>
            </div>
          </Router>

          <h2> Welcome :-) </h2>
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
            <WelcomeMsg />
            <button onClick={this.logout}>Logout</button>
          </div>)}
        <h3>{this.state.loginError} </h3>  
        <Router>
          <Switch>
            <Route exact path="/" render={() => <div></div>} />
            <Route path="/fetch" render={() => <div> <Fetching /> </div>} />
            <Route path="/fun" component={FunFact} />
            <Route component={NoMatch} />
          </Switch>
        </Router>

      </div>
    )
  }
}

export default App;
