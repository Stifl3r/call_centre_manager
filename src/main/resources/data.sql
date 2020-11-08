create schema ccm;

drop table if exists ccm.team;
create table ccm.team (
    teamId bigint generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
    name   varchar(50)
);
insert into ccm.team (name) values ( 'Paris Saint Germain');
insert into ccm.team (name) values ( 'FC Bayern Munich');

drop table if exists ccm.manager;
create table ccm.manager (
    managerId bigint generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
    firstname varchar(50),
    lastname  varchar(50),
    idNumber  varchar(13)
);
insert into ccm.manager (firstname, lastname, idNumber) values ( 'Jose', 'Mourinho', '7112235423086');

drop table if exists ccm.agent;
create table ccm.agent (
   agentId bigint generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
   firstname varchar(50),
   lastname  varchar(50),
   idNumber  varchar(13),
   managerId bigint references ccm.manager,
   teamId bigint references ccm.team
);
insert into ccm.agent (firstname, lastname, idnumber, managerId, teamId) values ( 'Julian', 'Gomez', '6503215423086', 1, 1);


