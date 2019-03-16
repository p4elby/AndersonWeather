import React from "react";
import axios from 'axios';
import moment from 'moment';
import Popup from "reactjs-popup"
export default class Search extends React.Component{
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
        check : 0
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
        this.setState({cityName: event.target.value});
    };
    onChangeDate = event =>{
        this.setState({date: moment(event.target.value).format('LL')});
    };
    checkCity = event =>{
        let cityNotFound = 0;
        this.state.forecast.map((field) =>{
                if (field.forecast_city === this.state.cityName && moment(field.date).format('LL') === this.state.date){
                    this.setState({visibility: field.visibility, pressure: field.presuure,
                        humidity: field.humidity, wind: field.wind, temp: field.temp, precipitation: field.precipitation, check: 1});
                    cityNotFound = 1;
                    return 1;
                }else return 0;
        }
        );
        if (cityNotFound === 0 ){
            alert("Forecast Not Found\n Check City Name or Date");
        }
        console.log("CheckCity")
    };
    render() {
        return(
            <div>
                <label> <span>City Name : </span>
                    <input
                        type = "text"
                        className = "form-control"
                        autoComplete = "off"
                        onChange={this.onChangeCityName}/>
                </label>
                <label> <span>Set Date :</span>
                    <input type = "date"
                           className = "form-control"
                           autoComplete = "off"
                           onChange = {this.onChangeDate}/>
                </label>
                <button className = "btn btn-primary" onClick={this.checkCity} style={{marginLeft: '5%',marginTop: '2%'}}>Search</button>
                <Popup contentStyle={{width: 550}} trigger = {<button className = "btn btn-primary" onClick={this.checkCity} style={{marginLeft: '5%',marginTop: '2%'}}>Show Forecast</button>} modal >
                    {close=>(
                        <div>
                            <table>
                                <thead>
                                <tr>
                                    <th>{this.state.cityName}</th>
                                    <th>{this.state.date}</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <th><span>Visibility: </span>{this.state.visibility}</th>
                                    <th><span>Wind : </span>{this.state.wind}</th>
                                    <th><span>Temp : </span>{this.state.temp}</th>
                                    <th><span>Humidity : </span>{this.state.humidity}</th>
                                    <th><span>Pressure : </span>{this.state.pressure}</th>
                                    <th><span>Precipitation : </span>{this.state.precipitation}</th>
                                </tr>
                                </tbody>
                            </table>
                            <button style = {{marginLeft: '43%', marginTop: '1%'}} className = "btn btn-danger"  onClick = {close}>Close</button>
                        </div>
                    )}
                </Popup>
            </div>
            )}}