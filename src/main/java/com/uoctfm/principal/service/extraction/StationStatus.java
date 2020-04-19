package com.uoctfm.principal.service.extraction;

import com.uoctfm.principal.domain.extraction.StationsStatusDTO;

public interface StationStatus {

    public StationsStatusDTO getListStationStatus(String systemStationEndPoints);

    public StationsStatusDTO getLastStationStatus(Integer id);

    public void saveLastStationStatus(StationsStatusDTO stationsStatus, Integer id);

}
