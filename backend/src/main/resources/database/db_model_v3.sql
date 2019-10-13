alter table app_user add phone varchar(1000);
update app_user set phone = '0123456789';
commit;
alter table app_user alter column phone set not null;