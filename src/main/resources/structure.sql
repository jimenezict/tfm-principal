CREATE TABLE uoc.systemconfiguration
(
  id integer PRIMARY KEY,
  name varchar(100),
  systemstationendpoint varchar(100),
  systemlocationendpoint varchar(100),
  saveinfilesystem boolean,
  saveintimeseries boolean,
  saveingis boolean
)

-- insert into  uoc.systemconfiguration (id, name, systemStationEndPoint, systemlocationendpoint, saveInFileSystem, saveInTimeSeries, saveInGIS)
-- values (1, 'test', 'http://localhost:8082/status', 'http://localhost:8082/location', false, false, false);

CREATE TABLE uoc.lastSample
(
  id integer PRIMARY KEY,
  time timestamp,
  lastSample varchar
);

CREATE SEQUENCE globalstatistical_id_seq START 1;

create table uoc.globalstatistical (
  id integer PRIMARY KEY NOT NULL DEFAULT nextval('globalstatistical_id_seq'),
  system integer,
  point geography(POINT),
  average decimal,
  entropy integer,
  entropyNormalized decimal
);

create sequence stationsystemraw_id_seq START 1;

create table uoc.stationsystemraw (
    id integer PRIMARY KEY NOT NULL DEFAULT nextval('stationsystemraw_id_seq'),
    system integer,
    station integer,
    point geography(POINT),
    stationSize integer,
    numBicicles integer
);