package com.uoctfm.principal.domain.load.databases.filesystem;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class BaseCsv {

    private LocalTime time;
    private LocalDate date;

    public BaseCsv() {
        time = LocalTime.now();
        date = LocalDate.now();
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }
}
