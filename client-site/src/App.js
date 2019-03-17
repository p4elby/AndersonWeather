import React, { Component } from 'react';
import './App.css';
import SearchAndAnalyze from './modules/SearchAndAnalyze'
import GenerateForecast from './modules/GenerateForecast'
class App extends Component {
  render() {
    return (
     <div className ="container" style={{marginTop: '4%',color: 'white'}}>
         <div className="row">
             <div className="col-sm">
                 <label>Search </label><SearchAndAnalyze/>
             </div>
             <div className="col-sm">
                 <label>Generate Data </label><GenerateForecast/>
             </div>
         </div>
     </div>
    );
  }
}

export default App;
