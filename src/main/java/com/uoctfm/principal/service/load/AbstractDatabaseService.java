package com.uoctfm.principal.service.load;

import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDatabaseService {

    Logger logger = LoggerFactory.getLogger(AbstractDatabaseService.class);
    private static String FILE_SYSTEM = "File System";
    private static String TIME_SERIES = "Time Series";
    private static String GIS = "GIS";

    protected StationDerived stationDerived;
    protected StationPercentils stationPercentils;
    protected StationRaw stationRaw;
    protected StationStatistics stationStatistics;

    protected String databaseType;
    protected String processName;

    public final void databaseServiceSetter(StationDerived stationDerived,
                                                   StationPercentils stationPercentils,
                                                   StationRaw stationRaw,
                                                   StationStatistics stationStatistics,
                                                   String databaseType,
                                                   String processName) {
        this.stationDerived = stationDerived;
        this.stationPercentils = stationPercentils;
        this.stationRaw = stationRaw;
        this.stationStatistics = stationStatistics;
        this.databaseType = databaseType;
        this.processName = processName;
    }

    public void databaseServiceExecutor(boolean executeStep) {
        if (executeStep) {
            initialize();
            saveRaw();
            saveDerived();
            savePercentils();
            logSuccessfulProcess(databaseType, processName);
            return;
        }
        logSkippingProcess(databaseType, processName);
    }

    public abstract void initialize();

    public abstract void saveRaw();

    public abstract void saveDerived();

    public abstract void savePercentils();

    private void logSuccessfulProcess(String database, String name) {
        logger.info("Success on saving on {} database for process {}", database, name);
    }

    private void logSkippingProcess(String database, String name) {
        logger.info("Skipping to save on {} database for process {}", database, name);
    }

}
