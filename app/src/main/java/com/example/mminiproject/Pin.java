package com.example.mminiproject;

public class Pin {
    private double lat, lng;

    public Pin(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Pin(){};

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
