insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('sevilla','http://localhost:8085/status/es/seville/sevici','http://localhost:8085/location/es/sevici',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('valencia','http://localhost:8085/status/es/valence/valenbisi','http://localhost:8085/location/es/valenbisi',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('santander','http://localhost:8085/status/es/santander/tusbic','http://localhost:8085/location/es/tusbic',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('dublin','http://localhost:8085/status/ie/dublin/dublinbikes','http://localhost:8085/location/ie/dublinbikes',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('marseille','http://localhost:8085/status/fr/marseille/levelo-mpm','http://localhost:8085/location/fr/levelo-mpm',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('toulouse','http://localhost:8085/status/fr/toulouse/velo.toulouse','http://localhost:8085/location/fr/velo.toulouse',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('ljubljana','http://localhost:8085/status/si/ljubljana/bicikelj','http://localhost:8085/location/si/bicikelj',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('bicing','http://localhost:8082/status','http://localhost:8082/location',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('zaragoza','http://localhost:8083/status', 'http://localhost:8083/location',true,true,true,true);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('mock10','http://localhost:9092/status/10', 'http://localhost:9092/location/10',true,true,true,false);

insert into uoc.systemconfiguration (name, systemstationendpoint, systemlocationendpoint, saveinfilesystem, saveintimeseries, saveingis, masterenable)
values ('mock200','http://localhost:9092/status/200', 'http://localhost:9092/location/200',true,true,true,false);



--  update uoc.systemconfiguration set masterenable = false;
--