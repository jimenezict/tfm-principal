package com.uoctfm.principal;

import java.time.Clock;

import static java.util.Objects.isNull;

public class ClockTest {

    private static Clock testClock;

    public static Clock getInstance(){
        if(isNull(testClock)){
            testClock = Clock.systemUTC();
        }
        return testClock;
    }

}
