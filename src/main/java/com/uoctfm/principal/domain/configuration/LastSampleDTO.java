package com.uoctfm.principal.domain.configuration;

import com.uoctfm.principal.domain.configuration.converter.JpaConverterJson;
import com.uoctfm.principal.domain.extraction.Station;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name="lastsample")
public class LastSampleDTO implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime time;

    @Convert(converter = JpaConverterJson.class)
    @Column(name = "lastsample")
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
