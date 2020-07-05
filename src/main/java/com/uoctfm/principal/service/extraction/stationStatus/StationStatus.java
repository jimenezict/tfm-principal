package com.uoctfm.principal.service.extraction.stationStatus;

import com.uoctfm.principal.domain.extraction.StationsStatusDTO;

public interface StationStatus {

    public StationsStatusDTO getListStationStatus(String systemStationEndPoints);

}
