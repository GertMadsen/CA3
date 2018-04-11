import React, { Component } from "react"
import facade from "./apiFacade";
import jwt_decode from 'jwt-decode';

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
    
    this.state = { dataFromServer: "Fetching!!", username : userName, userroles: userRoles };
  }
  componentDidMount() {
    facade.fetchData().then(res => this.setState({ dataFromServer: res }));
  }
  render() {
    return (
      <div>
        <h2>Data Received from server</h2>
        <h3>{this.state.dataFromServer}</h3>        
        <h3>Name: { this.state.username } - Roles: { this.state.userroles }</h3>        
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
            <LoggedIn />
            <button onClick={this.logout}>Logout</button>
          </div>)}
        <h3>{this.state.loginError} </h3>  
      </div>
    )
  }
}
export default App;
