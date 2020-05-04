package com.uoctfm.principal.domain.load.databases.gis;

import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="stationsystemraw")
public class StationSystemRaw implements Comparable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Integer system;

    Integer station;

    @Column(columnDefinition="geometry(Point,4326)")
    Point point;

    @Column(name = "stationsize")
    Integer stationSize;

    @Column(name = "numbicicles")
    Integer numBicicles;

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

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Integer getStationSize() {
        return stationSize;
    }

    public void setStationSize(Integer stationSize) {
        this.stationSize = stationSize;
    }

    public Integer getNumBicicles() {
        return numBicicles;
    }

    public void setNumBicicles(Integer numBicicles) {
        this.numBicicles = numBicicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationSystemRaw that = (StationSystemRaw) o;
        return system.equals(that.system) &&
                station.equals(that.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(system, station);
    }

    @Override
    public int compareTo(Object o) {
        if(this == o || (this.getSystem().intValue() == ((StationSystemRaw) o).getSystem().intValue() && this.getStation().intValue() == ((StationSystemRaw) o).getStation().intValue())) {
            return 0;
        }

        if(this.getSystem().intValue() > ((StationSystemRaw) o).getSystem().intValue()) {
            return 1;
        } else if (this.getSystem().intValue() < ((StationSystemRaw) o).getSystem().intValue()) {
            return -1;
        } else {
            if(this.getStation().intValue() > ((StationSystemRaw) o).getStation().intValue()) {
                return 1;
            } else if (this.getStation().intValue() < ((StationSystemRaw) o).getStation().intValue()) {
                return -1;
            }
        }
        return 0;
    }
}
