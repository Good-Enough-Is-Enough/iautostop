create table app_user
(
    id varchar(1000) not null
        constraint user_pk
            primary key,
    role varchar(100) not null
);

alter table app_user owner to postgres;

create unique index user_id_uindex
    on app_user (id);


create table app_user_route
(
    user_id varchar(1000) not null
        constraint user_route_pk
            primary key
        constraint user_route_user_id_fk
            references app_user,
    travel_from varchar(1000) not null,
    travel_to varchar(1000) not null,
    travel_street_list text
);

alter table app_user_route owner to postgres;

create unique index user_route_user_id_uindex
    on app_user_route (user_id);

