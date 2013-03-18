
drop database if exists shard1;

create database shard1;

use shard1;

drop table if exists USERS;

create table USERS (USER_ID binary(255) primary key, USER_NAME varchar(255), USER_GENDER varchar(10), USER_COUNTRY varchar(25));


drop database if exists shard2;

create database shard2;

use shard2;

drop table if exists USERS;

create table USERS (USER_ID binary(255) primary key, USER_NAME varchar(255), USER_GENDER varchar(10), USER_COUNTRY varchar(25));
