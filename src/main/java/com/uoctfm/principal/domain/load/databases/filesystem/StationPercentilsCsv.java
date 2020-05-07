package com.uoctfm.principal.domain.load.databases.filesystem;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.uoctfm.principal.domain.transformation.StationPercentils;

import java.io.Serializable;

@JsonPropertyOrder({"time","date"})
public class StationPercentilsCsv extends BaseCsv implements Serializable {

    private int p0, p1, p2, p3, p4, p5, p6, p7, p8, p9;

    public StationPercentilsCsv(StationPercentils stationPercentils) {
        super();
        this.p0 = stationPercentils.getP0();
        this.p1 = stationPercentils.getP1();
        this.p2 = stationPercentils.getP2();
        this.p3 = stationPercentils.getP3();
        this.p4 = stationPercentils.getP4();
        this.p5 = stationPercentils.getP5();
        this.p6 = stationPercentils.getP6();
        this.p7 = stationPercentils.getP7();
        this.p8 = stationPercentils.getP8();
        this.p9 = stationPercentils.getP9();
    }

    public int getP0() {
        return p0;
    }

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public int getP3() {
        return p3;
    }

    public int getP4() {
        return p4;
    }

    public int getP5() {
        return p5;
    }

    public int getP6() {
        return p6;
    }

    public int getP7() {
        return p7;
    }

    public int getP8() {
        return p8;
    }

    public int getP9() {
        return p9;
    }
}
