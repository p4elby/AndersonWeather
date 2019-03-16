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
                    <label>City Name :
                        <input type = "text"
                               className = "form-control"
                               autoComplete = "off"
                               onChange = {this.onCityChange}/>
                    </label>
                    <label>Date :
                        <input type = "date"
                               className = "form-control"
                               autoComplete = "off"
                               onChange = {this.onDateChange}/>
                    </label>
                </div>
                <div>
                    <button  style = {{marginLeft: '25% '}} type = "submit" className = "btn btn-primary" onClick = {this.onSubmit}>
                        Generate
                    </button>
                </div>
            </div>
        )
    }
}