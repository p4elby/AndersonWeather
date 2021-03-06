import React from "react";
import axios from 'axios';
import moment from 'moment';
import PopUp from 'reactjs-popup'
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
        check : "start",
        analyzeData : ""
    };
    getForecast = () =>{
        axios.get('http://localhost:8300/api/db-service/db/forecast')
            .then(res =>{
                let forecast = res.data;
                this.setState({forecast})
        });
    };
    getAnalyze = () =>{
        let temp = this.state.temp;
        let wind = this.state.wind;
        let precipitation = this.state.precipitation;
        axios.post('http://localhost:8300/api/analyze-data/analyze-service/analyze', {temperature: temp, wind: wind, precipitation: precipitation}).then(res=>{
            this.setState({analyzeData : res.data})
        });
    };
    componentDidMount() {
        this.getForecast();
        this.interval = setInterval(() => this.getForecast(), 10000);
    }
    componentWillUnmount() {
        clearInterval(this.interval)
    }
    onChangeCityName = event =>{
        this.setState({cityName: event.target.value, check : "start"});
    };
    onChangeDate = event =>{
        this.setState({date: moment(event.target.value).format('LL'), check : "start"});
    };
    checkCity = () =>{
        this.setState({check: "notFound"});
        this.state.forecast.map((field) =>{
                if ((field.forecast_city).toLowerCase() === (this.state.cityName).toLowerCase().trim() && moment(field.date).format('LL') === this.state.date){
                    this.setState({check: "show", visibility: field.visibility, pressure: field.presuure,
                        humidity: field.humidity, wind: field.wind, temp: field.temp, precipitation: field.precipitation, analyzeData: "", cityName: field.forecast_city});
                    this.getAnalyze();
                    return 1;
                }else return 0;
        });
    };
    showForecast =()=>{
        if (this.state.check === "show")
        return (
            <div style={{marginTop: '3%'}}>
                <table>
                    <thead>
                    <tr>
                        <th>{this.state.cityName}</th>
                        <th>{this.state.date}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td><span>Visibility : </span>{this.state.visibility}</td></tr>
                    <tr><td><span>Wind : </span>{this.state.wind}</td></tr>
                    <tr><td><span>Temp : </span>{this.state.temp}</td></tr>
                    <tr><td><span>Humidity : </span>{this.state.humidity}</td></tr>
                    <tr><td><span>Pressure : </span>{this.state.pressure}</td></tr>
                    <tr><td><span>Precipitation : </span>{this.state.precipitation}</td></tr>
                    </tbody>
                </table>
                <PopUp trigger={<button className = "btn btn-primary" > Analyze</button>} modal>{close =>(
                    <div style={{color: 'black'}}>
                        {this.state.analyzeData}<br/>
                        <button className = "btn btn-danger"  style={{marginLeft: '42%', marginTop: '1%'}} onClick = {close}>Close</button>
                    </div>
                )}
                </PopUp>
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
                <button disabled={!this.state.date || !this.state.cityName} className = "btn btn-primary" onClick={this.checkCity} style={{marginLeft: '1%', marginBottom: '1%'}}>Search</button>
                {this.showForecast()}
                {this.forecastNotFound()}
            </div>
        )}}