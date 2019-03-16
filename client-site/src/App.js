import React, { Component } from 'react';

import './App.css';
import Search from './modules/Search'
import GenerateForecast from './modules/GenerateForecast'
class App extends Component {
  render() {
    return (
     <div>
        {/*<div><Search/></div>*/}
        <div><GenerateForecast/></div>
     </div>

    );
  }
}

export default App;
