package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.StationStatusDTO;

public interface StationStatus {

    public StationStatusDTO getListStationStatus(String systemStationEndPoints);

    public StationStatusDTO getLastListStationStatus(Integer id);

}
