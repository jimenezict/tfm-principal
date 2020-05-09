package com.uoctfm.principal.domain.load.databases.gis;

import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="globalstatistical")
public class GlobalStatistical {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    Integer system;
    String name;
    @Column(columnDefinition="geometry(Point,4326)")
    Point point;
    double average;
    Integer entropy;
    @Column(name = "entropynormalized")
    double entropyNormalized;
    double longitude;
    double latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Integer getEntropy() {
        return entropy;
    }

    public void setEntropy(Integer entropy) {
        this.entropy = entropy;
    }

    public double getEntropyNormalized() {
        return entropyNormalized;
    }

    public void setEntropyNormalized(double entropyNormalized) {
        this.entropyNormalized = entropyNormalized;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
