ALTER USER postgres PASSWORD 'Passw0rd';

CREATE SCHEMA uoc;

CREATE SEQUENCE uoc.systemconfiguration_id_seq START 1;
CREATE TABLE uoc.systemconfiguration
(
  id integer PRIMARY KEY NOT NULL DEFAULT nextval('uoc.systemconfiguration_id_seq'),
  name character varying(100),
  systemstationendpoint character varying(100),
  systemlocationendpoint character varying(100),
  saveinfilesystem boolean,
  saveintimeseries boolean,
  saveingis boolean
);

alter table uoc.systemconfiguration add column masterenable boolean default false;

CREATE SEQUENCE uoc.lastSample_id_seq START 1;
CREATE TABLE uoc.lastSample
(
  id integer PRIMARY KEY NOT NULL DEFAULT nextval('uoc.lastSample_id_seq'),
  time timestamp,
  lastSample varchar
);

CREATE SEQUENCE uoc.globalstatistical_id_seq START 1;
create table uoc.globalstatistical (
  id integer PRIMARY KEY NOT NULL DEFAULT nextval('uoc.globalstatistical_id_seq'),
  system integer,
  name varchar,
  point geography(POINT),
  average decimal,
  entropy integer,
  entropyNormalized decimal,
  longitude decimal,
  latitude decimal
);

create sequence uoc.stationsystemraw_id_seq START 1;
create table uoc.stationsystemraw (
    id integer PRIMARY KEY NOT NULL DEFAULT nextval('uoc.stationsystemraw_id_seq'),
    system integer,
    station integer,
    point geography(POINT),
    stationSize integer,
    numBicicles integer,
    address varchar,
    longitude decimal,
    latitude decimal
);

create sequence uoc.systemstatistics_id_seq START 1;
create table uoc.systemstatistics (
    id integer PRIMARY KEY NOT NULL DEFAULT nextval('uoc.systemstatistics_id_seq'),
    systemid integer,
    executiontime timestamp,
    executioninmiliseconds integer
);