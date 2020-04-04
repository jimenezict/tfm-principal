package com.uoctfm.principal.domain.station.calculated;

import java.io.Serializable;

public class StationPercentils implements Serializable {

    private int p0, p1, p2, p3, p4, p5, p6, p7, p8, p9;

    public StationPercentils(int p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.p8 = p8;
        this.p9 = p9;
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
