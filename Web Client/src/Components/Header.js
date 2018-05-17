import React, { Component } from "react"
import {  HashRouter as Router } from 'react-router-dom'
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

  export default Header;