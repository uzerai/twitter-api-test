import React, { Component } from 'react';
import Search from "../components/Search/Search"

class Layout extends Component {
  render() {
    return (
      <>
        <section className="hero is-dark">
          <div className="hero-body">
            <div className="container">
              <h1 className="title">{process.env.REACT_APP_APPLICATION_NAME}</h1>
              <h2 className="subtitle">A Micronaut/React stack project.</h2>
            </div>
          </div>
        </section>
        <section className="section">
          <Search></Search>
        </section>
      </>
    );
  }
}

export default Layout;
