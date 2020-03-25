package com.uoctfm.princial.domain.station;

import java.time.LocalDateTime;

public class StationStatusDTO {

    private LocalDateTime executionDateTime;

    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    public void setExecutionDateTime(LocalDateTime executionDateTime) {
        this.executionDateTime = executionDateTime;
    }
}
