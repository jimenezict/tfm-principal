package com.uoctfm.principal.domain.configuration;

import com.uoctfm.principal.domain.configuration.converter.JpaConverterJson;
import com.uoctfm.principal.domain.station.Station;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name="lastSample")
public class LastSampleDTO implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private LocalDateTime time;

    @Convert(converter = JpaConverterJson.class)
    private Map<Integer, Station> lastSample;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Map<Integer, Station> getLastSample() {
        return lastSample;
    }

    public void setLastSample(Map<Integer, Station> lastSample) {
        this.lastSample = lastSample;
    }
}
