package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.load.databases.filesystem.StationDerivedCsv;
import com.uoctfm.principal.domain.load.databases.filesystem.StationPercentilsCsv;
import com.uoctfm.principal.domain.load.databases.filesystem.StationRawCsv;
import com.uoctfm.principal.domain.load.databases.filesystem.StationStatisticsCsv;
import com.uoctfm.principal.domain.transformation.StationDataWrapper;
import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.repository.load.filesystem.FoldersRepository;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class FileSystemDatabaseService extends AbstractDatabaseService {

    @Autowired
    FoldersRepository foldersRepository;

    Logger logger = getLogger(FileSystemDatabaseService.class);

    @Override
    public void initialize(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        String processName = systemConfigurationDTO.getName();

        if(!foldersRepository.hasSystemFolder(processName)) {
            logger.info("Creating system folder {}", processName);
            foldersRepository.createSystemFolder(processName);
        }

        if(!foldersRepository.hasDateFolder(processName, now())) {
            logger.info("Creating date folder for system {}", processName);
            foldersRepository.createDateFolder(processName, now());
        }
    }

    @Override
    public void saveRaw(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        List<Object> stationList = new ArrayList<>();

        stationDataWrapper
                .getStationRaw()
                .getStationStatusDTO()
                .getStationList()
                .values()
                .forEach(station -> stationList.add(new StationRawCsv(station)));

        foldersRepository.writeListOnFile(stationList,  "raw", systemConfigurationDTO);
    }

    @Override
    public void saveStatistics(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        foldersRepository.writeListOnFile(asList(new StationPercentilsCsv(stationDataWrapper.getStationPercentils())),  "percentils", systemConfigurationDTO);
        foldersRepository.writeListOnFile(asList(new StationStatisticsCsv(stationDataWrapper.getStationStatistics())),  "statistics", systemConfigurationDTO);
    }

    @Override
    public void saveDerived(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        List<Object> stationDerivedCsv = new ArrayList<>();
        StationDerived stationDerived = stationDataWrapper.getStationDerived();
        stationDerived.getStationsStatusDTO().keySet().forEach(x -> {
            stationDerivedCsv.add(new StationDerivedCsv(x, stationDerived.getStationsStatusDTO().get(x)));
        });

        foldersRepository.writeListOnFile(stationDerivedCsv, "derived", systemConfigurationDTO);
    }

}
