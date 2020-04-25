package com.uoctfm.principal.service.load;

import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDatabaseService {

    Logger logger = LoggerFactory.getLogger(AbstractDatabaseService.class);
    protected static String FILE_SYSTEM = "File System";
    protected static String TIME_SERIES = "Time Series";
    protected static String GIS = "GIS";

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
            logSuccessfulProcess();
            return;
        }
        logSkippingProcess();
    }

    public abstract void initialize();

    public abstract void saveRaw();

    public abstract void saveDerived();

    public abstract void savePercentils();

    private void logSuccessfulProcess() {
        logger.info("Success on saving on {} database for process {}", databaseType, processName);
    }

    private void logSkippingProcess() {
        logger.info("Skipping to save on {} database for process {}", databaseType, processName);
    }

}