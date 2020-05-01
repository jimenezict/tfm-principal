package com.uoctfm.principal.domain.load.databases;

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

    @Column(columnDefinition="geometry(Point,4326)")
    Point point;

    double average;
    Integer entropy;
    @Column(name = "entropynormalized")
    double entropyNormalized;

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
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
}
