import React from 'react';
import axios from 'axios';
import moment from 'moment';
export default class GenerateForecast extends React.Component{
    state = {
        forecast :[],
        date : "",
        city : "",
    };
    onCityChange = event =>{
      this.setState({city : event.target.value})
    };
    onDateChange = event =>{
        this.setState({date: event.target.value});
        console.log(this.state.date)
    };
    getForecast = () =>{
        axios.get('http://localhost:8300/api/db-service/db/forecast')
            .then(res =>{
                let forecast = res.data;
                this.setState({forecast})
            });
    };
    componentDidMount() {
        this.getForecast();
    }

    onSubmit = event =>{
        event.preventDefault();
        let date = this.state.date;
        let city = this.state.city;
        if (city === ""){
            return(
                alert("City is Null!"))
        } else {
            if (date === ""){
                return(
                    alert("Date is Null!")
                )
            } else {
                axios.post('http://localhost:8300/api/random-data/random/generate', {city: city, date: date}).then(res=>{
                    if (res.data === 'No'){
                        alert("Forecast with such parameters already exists")
                    }else {
                        alert("Data Add");
                        this.getForecast();
                    }
                })
            }
        }
    };
    render() {
        return(
            <div>
                <div>
                    <label><span>City Name : </span>
                        <input type = "text"
                               className = "form-control"
                               autoComplete = "off"
                               onChange = {this.onCityChange}/>
                    </label>
                    <label style={{marginLeft: '2%'}}><span> Date : </span>
                        <input type = "date"
                               className = "form-control"
                               autoComplete = "off"
                               onChange = {this.onDateChange}/>
                    </label>
                    <button className = "btn btn-primary" style = {{marginLeft: '1%'}} onClick = {this.onSubmit}>
                        Generate
                    </button>
                </div>
                <div style={{marginTop: '3%',display:'block', overflow: 'auto',height: '500px'}}>
                    {this.state.forecast.map((field,index)=>
                        <table key={index} >
                            <thead>
                            <tr>
                                <th>{field.forecast_city}</th>
                                <th>{moment(field.date).format('LL')}</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr><td><span>Visibility : </span>{field.visibility}</td></tr>
                                <tr><td><span>Wind : </span>{field.wind}</td></tr>
                                <tr><td><span>Temp : </span>{field.temp}</td></tr>
                                <tr><td><span>Humidity : </span>{field.humidity}</td></tr>
                                <tr><td><span>Pressure : </span>{field.pressure}</td></tr>
                                <tr><td><span>Precipitation : </span>{field.precipitation}</td></tr>
                            </tbody>
                        </table>
                    )}
                </div>
            </div>
        )
    }
}