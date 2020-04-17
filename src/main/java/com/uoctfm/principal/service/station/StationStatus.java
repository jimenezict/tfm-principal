package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.StationsStatusDTO;

public interface StationStatus {

    public StationsStatusDTO getListStationStatus(String systemStationEndPoints);

    public StationsStatusDTO getLastStationStatus(Integer id);

    public void saveLastStationStatus(StationsStatusDTO stationsStatus, Integer id);

}
