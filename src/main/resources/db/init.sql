create database if not exists genesis;

use genesis;

create table if not exists user (
    id bigint primary key auto_increment,
    name varchar(255) not null,
    surname varchar(255) not null,
    person_id varchar(50) not null unique,
    uuid varchar(255) not null unique
)