

CREATE TABLE uoc.systemconfiguration
(
  id integer PRIMARY KEY,
  name character(100),
  systemstationendpoint character(100),
  saveinfilesystem boolean,
  saveintimeseries boolean,
  saveingis boolean
)

insert into  uoc.systemconfiguration (id, name, systemStationEndPoint, saveInFileSystem, saveInTimeSeries, saveInGIS)
values (1, 'test', 'http://localhost:8082/', false, false, false);