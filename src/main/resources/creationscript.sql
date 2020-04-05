

CREATE TABLE uoc.systemconfiguration
(
  id integer,
  name character(100),
  systemStationEndPoint character(100),
  saveInFileSystem boolean,
  saveInTimeSeries boolean,
  saveInGIS boolean
);

insert into  uoc.systemconfiguration (id, name, systemStationEndPoint, saveInFileSystem, saveInTimeSeries, saveInGIS)
values (1, 'test', 'http://localhost:8082/asdfasdf', false, false, false);