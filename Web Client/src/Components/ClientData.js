import React, { Component } from "react";
import facade from "../apiFacade";
import { Link } from 'react-router-dom'

export default class ClientData extends Component {
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

