import React, { Component } from 'react';
import { search } from '../../util/api/SearchApi'

class SearchField extends Component {
  abortController = new AbortController();
  signal = this.abortController.signal;

  state = {
    value: null
  }

  handleChange = async (event) => {
    this.setState({ value: event.target.value });
  }

  performSearch = async () => {
    const { setResults } = this.props;
    const { value } = this.state;

    if (value) {
      const results = await search(value, this.signal);
      setResults(results);
    }
  }

  render() {
    return <>
      <div className="field has-addons is-flex">
        <div className="control">
          <input className="input" type="text" placeholder="Search some tweets." onChange={this.handleChange}></input>
        </div>
        <div className="control">
          <input type="button" className="button is-info" onClick={this.performSearch} value="Search"></input>
        </div>
      </div>
    </>;
  }
}

export default SearchField;
