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

    onSubmit = event =>{
        event.preventDefault();
        let date = this.state.date;
        let city = this.state.city;

        console.log(moment(date).format('LL'));
        if (city === ""){
            return(
                alert("City is Null!"))
        } else {
            if (date === ""){
                return(
                    alert("Date is Null!")
                )
            } else {
                console.log(city);
                console.log(date);
                axios.post('http://localhost:8300/api/random-data/random/generate', {city: city, date: date}).then(res=>{
                    alert("Data Add");
                    console.log(res);
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
            </div>
        )
    }
}