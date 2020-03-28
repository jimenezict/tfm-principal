package com.uoctfm.principal.domain.configuration;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemStationEndPoint() {
        return systemStationEndPoint;
    }

    public void setSystemStationEndPoint(String systemStationEndPoint) {
        this.systemStationEndPoint = systemStationEndPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSaveInFileSystem() {
        return saveInFileSystem;
    }

    public void setSaveInFileSystem(Boolean saveInFileSystem) {
        this.saveInFileSystem = saveInFileSystem;
    }

    public Boolean getSaveInTimeSeries() {
        return saveInTimeSeries;
    }

    public void setSaveInTimeSeries(Boolean saveInTimeSeries) {
        this.saveInTimeSeries = saveInTimeSeries;
    }

    public Boolean getSaveInGIS() {
        return saveInGIS;
    }

    public void setSaveInGIS(Boolean saveInGIS) {
        this.saveInGIS = saveInGIS;
    }
}
