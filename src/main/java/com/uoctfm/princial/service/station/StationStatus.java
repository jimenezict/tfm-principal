package com.uoctfm.princial.service.station;

import com.uoctfm.princial.domain.station.StationStatusDTO;

public interface StationStatus {

    public StationStatusDTO getListStationStatus(String systemStationEndPoints);

    public StationStatusDTO getLastListStationStatus(Integer id);

}
