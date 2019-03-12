package com.randomData.enums;

public enum Wind {
    CALM("Calm"),
    QUIET("Quiet"),
    LIGHT("Light"),
    WEAK("Weak"),
    MODERATE("Moderate"),
    FRESH("Fresh"),
    STRONG("Strong"),
    STORM("Storm"),
    HURRICANE("Hurricane");
    public String wind;
    Wind(String wind){
        this.wind = wind;
    }
    public String getWind(){
        return this.wind;
    }
}
