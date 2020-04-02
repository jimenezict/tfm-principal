

CREATE TABLE principal.systemconfiguration
(
  id integer,
  name character(100),
  "systemStationEndPoint" character(100),
  "saveInFileSystem" boolean,
  "saveInTimeSeries" boolean,
  "saveInGIS" boolean
)