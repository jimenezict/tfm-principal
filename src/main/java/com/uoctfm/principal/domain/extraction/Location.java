package com.uoctfm.principal.domain.extraction;

public class Location {

    private int id;
    private double latitude;
    private double longitude;
    private String address;

    public Location(int id, double latitude, double longitude, String address) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

}
