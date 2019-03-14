package com.randomData.enums;

public  enum Visibility {
    LIGHT ("LightFog"),
    HEAVY ("HeavyFog"),
    GOOD ("Good");

    private String vis;

    Visibility(String visibility) {
        this.vis = visibility;
    }
    public String getVis(){
        return this.vis;
    }
}
