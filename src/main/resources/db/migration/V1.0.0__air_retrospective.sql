CREATE SCHEMA IF NOT EXISTS data;
set search_path = 'data';


CREATE EXTENSION postgis;



create table country
(
    id_country  integer generated always as identity (maxvalue 100)
        primary key,
    name        text not null,
    description text not null
);

create table param
(
    id_param    integer not null
        primary key,
    name        text    not null,
    description text    not null
);


create table postcode_of_station
(
    id_postcode_of_station bigint  not null
        constraint postcode_pkey
            primary key,
    postcode               text    not null,
    country                integer not null,
    geom                   geometry,
    latitude               double precision,
    longitude              double precision
);


create table measurement_on_postcode_of_station
(
    id          bigint generated always as identity (maxvalue 100000000000000000)
        primary key,
    measurement double precision         not null,
    postcode    bigint                   not null,
    timestamp   timestamp with time zone not null,
    param       integer                  not null
);


create table postcode_of_patient
(
    id_postcode_of_patient bigint  not null
        primary key,
    postcode               text    not null,
    country                integer not null,
    geom                   geometry,
    hash_code              text,
    latitude               double precision,
    longitude              double precision,
    matching_post_code     bigint,
    distance               double precision
);


create table station_on_postcode
(
    id_station   bigint           not null
        primary key,
    station_code text             not null,
    latitude     double precision not null,
    longitude    double precision not null,
    postcode_id  bigint           not null,
    geom         geometry
);

