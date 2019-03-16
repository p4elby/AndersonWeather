import React from "react";
import axios from 'axios';
import moment from 'moment';
export default class SearchAndAnalyze extends React.Component{
    state = {
        forecast:[],
        cityName: "",
        date: "",
        visibility: "",
        pressure: 0,
        humidity: 0,
        wind: "",
        temp: 0,
        precipitation: 0,
        check : "start"
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
    onChangeCityName = event =>{
        this.setState({cityName: event.target.value, check : "start"});
    };
    onChangeDate = event =>{
        this.setState({date: moment(event.target.value).format('LL')});
    };
    checkCity = event =>{
        this.setState({check: "notFound"});
        this.state.forecast.map((field) =>{
                if (field.forecast_city === this.state.cityName && moment(field.date).format('LL') === this.state.date){
                    this.setState({check: "show", visibility: field.visibility, pressure: field.presuure,
                        humidity: field.humidity, wind: field.wind, temp: field.temp, precipitation: field.precipitation});
                    return 1;
                }else return 0;
        }
        );
        console.log("CheckCity")
    };
    showForecast =()=>{
        if (this.state.check === "show")
        return (
            <div>
                <table>
                    <thead>
                    <tr>
                        <td>{this.state.cityName}</td>
                        <td>{this.state.date}</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <tr><span>Visibility : </span>{this.state.visibility}</tr>
                        <tr><span>Wind : </span>{this.state.wind}</tr>
                        <tr><span>Temp : </span>{this.state.temp}</tr>
                        <tr><span>Humidity : </span>{this.state.humidity}</tr>
                        <tr><span>Pressure : </span>{this.state.pressure}</tr>
                        <tr><span>Precipitation : </span>{this.state.precipitation}</tr>
                    </tr>
                    </tbody>
                </table>
            </div>)
    };
    forecastNotFound =()=>{
        if (this.state.check === "notFound")
        return(
            <div><b>Forecast Not Found! Check Date or City! Or generate Forecast</b></div>
        )
    };
    render() {
        return(
            <div>
                <label><span>City Name : </span>
                    <input
                        type = "text"
                        className = "form-control"
                        autoComplete = "off"
                        onChange={this.onChangeCityName}/>
                </label>
                <label style={{marginLeft: '2%'}}><span> Date : </span>
                    <input type = "date"
                           className = "form-control"
                           autoComplete = "off"
                           onChange = {this.onChangeDate}/>
                </label>
                <button className = "btn btn-primary" onClick={this.checkCity} style={{marginLeft: '1%'}}>Search</button>
                {this.showForecast()}
                {this.forecastNotFound()}
            </div>
        )}}