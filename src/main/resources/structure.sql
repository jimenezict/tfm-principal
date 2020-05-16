ALTER USER postgres PASSWORD 'Passw0rd';

CREATE SCHEMA uoc;

CREATE TABLE uoc.systemconfiguration
(
  id integer NOT NULL,
  name character varying(100),
  systemstationendpoint character varying(100),
  systemlocationendpoint character varying(100),
  saveinfilesystem boolean,
  saveintimeseries boolean,
  saveingis boolean,
  masterenable boolean DEFAULT false,
  CONSTRAINT systemconfiguration_pkey PRIMARY KEY (id)
);

alter table uoc.systemconfiguration add column masterenable boolean default false;

CREATE SEQUENCE lastSample_id_seq START 1;
CREATE TABLE uoc.lastSample
(
  id integer PRIMARY KEY NOT NULL DEFAULT nextval('lastSample_id_seq'),
  time timestamp,
  lastSample varchar
);

CREATE SEQUENCE globalstatistical_id_seq START 1;
create table uoc.globalstatistical (
  id integer PRIMARY KEY NOT NULL DEFAULT nextval('globalstatistical_id_seq'),
  system integer,
  name varchar,
  point geography(POINT),
  average decimal,
  entropy integer,
  entropyNormalized decimal,
  longitude decimal,
  latitude decimal
);

create sequence stationsystemraw_id_seq START 1;
create table uoc.stationsystemraw (
    id integer PRIMARY KEY NOT NULL DEFAULT nextval('stationsystemraw_id_seq'),
    system integer,
    station integer,
    point geography(POINT),
    stationSize integer,
    numBicicles integer,
    address varchar,
    longitude decimal,
    latitude decimal
);