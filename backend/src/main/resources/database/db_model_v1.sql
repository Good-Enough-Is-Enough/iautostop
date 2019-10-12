create table "user"
(
    id varchar(1000) not null
        constraint user_pk
            primary key,
    role varchar(100) not null
);

alter table "user" owner to postgres;

create unique index user_id_uindex
    on "user" (id);


create table user_route
(
    user_id varchar(1000) not null
        constraint user_route_pk
            primary key
        constraint user_route_user_id_fk
            references "user",
    travel_from varchar(1000) not null,
    travel_to varchar(1000) not null,
    travel_street_list text not null
);

alter table user_route owner to postgres;

create unique index user_route_user_id_uindex
    on user_route (user_id);

