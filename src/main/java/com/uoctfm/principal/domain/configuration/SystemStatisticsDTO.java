package com.uoctfm.principal.domain.configuration;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="systemstatistics")
public class SystemStatisticsDTO implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "systemid")
    private Integer systemid;
    @Column(name = "executiontime")
    private LocalDateTime executionTime;
    @Column(name = "executioninmiliseconds")
    private Integer executionInMiliseconds;

    public SystemStatisticsDTO(Integer systemid, LocalDateTime executionTime, Integer executionInMiliseconds) {
        this.systemid = systemid;
        this.executionTime = executionTime;
        this.executionInMiliseconds = executionInMiliseconds;
    }

    public Integer getId() {
        return id;
    }

}
