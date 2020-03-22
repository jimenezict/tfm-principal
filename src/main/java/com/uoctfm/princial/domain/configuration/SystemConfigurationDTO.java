package com.uoctfm.princial.domain.configuration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemConfigurationDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String systemStationEndPoint;
    private String name;
    private Boolean saveInFileSystem;
    private Boolean saveInTimeSeries;
    private Boolean saveInGIS;

    public Integer getId() {
        return id;
    }

    public String getSystemStationEndPoint() {
        return systemStationEndPoint;
    }

    public String getName() {
        return name;
    }

    public Boolean getSaveInFileSystem() {
        return saveInFileSystem;
    }

    public Boolean getSaveInTimeSeries() {
        return saveInTimeSeries;
    }

    public Boolean getSaveInGIS() {
        return saveInGIS;
    }
}
