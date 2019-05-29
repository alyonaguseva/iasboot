-- Версия БД
create table versions(
  id integer,
  name varchar(64),
  version varchar(16),
  primary key (id)
);

-- Связь с Bing3 наших сигналов
create table bing3_signal_mapping (
  id integer references signal(id) NOT NULL,
  id_external_signal varchar(100),
  primary key(id)
);

-- Таблица обмена
create table bing3_exchange(
  id serial,
  id_external_signal varchar(100) NOT NULL,
  value float NOT NULL,
  calculated_value float,
  value_time TIMESTAMP,
  primary key(id)
);

insert into versions values (1, 'db', '6');

update versions set version = '6' where name = 'db';

