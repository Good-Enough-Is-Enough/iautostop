alter table app_user add name varchar(1000);
update app_user set name = 'userName';
commit;
alter table app_user alter column name set not null;