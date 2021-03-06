package com.uoctfm.principal.domain.configuration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="systemconfiguration")
public class SystemConfigurationDTO implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "systemstationendpoint")
    private String systemStationEndPoint;
    @Column(name = "systemlocationendpoint")
    private String systemLocationEndPoint;
    private String name;
    @Column(name = "saveinfilesystem")
    private Boolean saveInFileSystem;
    @Column(name = "saveintimeseries")
    private Boolean saveInTimeSeries;
    @Column(name = "saveingis")
    private Boolean saveInGIS;
    @Column(name = "masterenable")
    private Boolean masterEnable;

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

    public String getSystemLocationEndPoint() {
        return systemLocationEndPoint;
    }

    public void setSystemLocationEndPoint(String systemLocationEndPoint) {
        this.systemLocationEndPoint = systemLocationEndPoint;
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

    public Boolean getMasterEnable() {
        return masterEnable;
    }

    public void setMasterEnable(Boolean masterEnable) {
        this.masterEnable = masterEnable;
    }
}
