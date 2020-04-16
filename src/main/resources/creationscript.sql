

CREATE TABLE uoc.systemconfiguration
(
  id integer PRIMARY KEY,
  name character(100),
  systemstationendpoint character(100),
  systemlocationendpoint character(100),
  saveinfilesystem boolean,
  saveintimeseries boolean,
  saveingis boolean
)

insert into  uoc.systemconfiguration (id, name, systemStationEndPoint, systemlocationendpoint, saveInFileSystem, saveInTimeSeries, saveInGIS)
values (1, 'test', 'http://localhost:8082/status', 'http://localhost:8082/location', false, false, false);