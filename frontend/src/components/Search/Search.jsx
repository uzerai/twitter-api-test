import React, { Component } from 'react';
import ResultsSection from './ResultsSection';
import SearchField from './SearchField'

class Search extends Component {
  state = {
    results: null
  }

  setResults = async (results) => {
    this.setState({
      results
    });
  }

  render() {
    const { results } = this.state;

    return <>
      <div className="container">
        <SearchField setResults={this.setResults}></SearchField>
        <hr></hr>
        {
          results
          ?  <ResultsSection results={results}></ResultsSection>
          :  <NoResultsFound></NoResultsFound>
        }
      </div>
    </>;
  }
}

const NoResultsFound = () => {
  return <>
    <div className="container has-text-centered">
      <h3>Looks like there's nothing here...</h3>
    </div>
  </>
}

export default Search;
