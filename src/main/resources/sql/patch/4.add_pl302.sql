-- Настройки pl302
create table pl302(
  id integer REFERENCES sensor(id) NOT NULL,
  name varchar(128) not null,
  url varchar(512) not null,
  password varchar(512) not null,
  primary key(id)
);

alter table sensor_setting alter column tag_name drop not null;

alter table sensor_setting add id_pl302 integer REFERENCES pl302(id);

