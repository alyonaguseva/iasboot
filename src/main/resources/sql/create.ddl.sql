-- Версия БД
create table versions(
  id integer,
  name varchar(64),
  version varchar(16),
  primary key (id)
);

-- Пользователи, пароль хранится в md5
create table users(
  id serial,
  username varchar(32),
  password varchar(128),
  primary key(id)
);

-- Объекты мониторинга
create table object_monitor(
  id integer UNIQUE NOT NULL,
  name varchar(128) UNIQUE NOT NULL,
  primary key(id)
);

-- Измеряемый параметр
create table measured_parameter(
  id integer UNIQUE NOT NULL,
  name varchar(128) UNIQUE NOT NULL,
  data_type integer,
  primary key(id)
);

-- Датчики
create table sensor(
  id integer UNIQUE NOT NULL,
  name varchar(128) NOT NULL,
  id_object_monitor integer REFERENCES object_monitor(id) NOT NULL,
  type varchar(32) NOT NULL,
  on_sensor integer NOT NULL,
  in_tag BOOLEAN,
  primary key(id)
);

-- Настройки pl302

create table pl302(
  id integer REFERENCES sensor(id) NOT NULL,
  name varchar(128) not null,
  url varchar(512) not null,
  password varchar(512) not null,
  primary key(id)
);

-- Настройки датчиков
create table sensor_setting (
  id integer REFERENCES sensor(id) NOT NULL,
  tag_name varchar(128) NOT NULL,
  id_pl302 integer REFERENCES pl302(id),
  primary key(id)
);

-- Тип сигнала
create table signal_type(
  id integer UNIQUE NOT NULL,
  name varchar(128) UNIQUE NOT NULL,
  primary key(id)
);

-- Входные сигналы
create table signal(
  id integer UNIQUE NOT NULL,
  id_sensor integer REFERENCES sensor(id) NOT NULL,
  id_type integer REFERENCES signal_type(id) NOT NULL,
  id_measured_parameter INTEGER REFERENCES measured_parameter(id) not null,
  in_tag BOOLEAN,
  primary key(id)
);

-- Настройки сигналов
create table signal_setting (
    id integer REFERENCES signal(id) NOT NULL,
    tag_name varchar(128) NOT NULL,
    id_pl302 integer REFERENCES pl302(id),
    primary key(id)
);

-- Состояние задания
create table task_status(
  id integer UNIQUE NOT NULL,
  name VARCHAR(64) UNIQUE NOT NULL,
  systemname VARCHAR(256) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

-- Задания на опрос датчиков
create table task(
  id serial,
  id_ids bigint not null,
  id_signal integer REFERENCES signal(id),
  id_output_signal integer REFERENCES signal(id),
  date TIMESTAMP,
  date_max TIMESTAMP,
  status integer REFERENCES task_status(id),
  complete BOOLEAN,
  PRIMARY KEY(id)
);

-- Тип лога выполнения задания
create table task_log_type(
  id integer UNIQUE NOT NULL,
  name VARCHAR(256) UNIQUE NOT NULL,
  systemname VARCHAR(256) UNIQUE NOT NULL,
  PRIMARY KEY(id)
);

-- Лог выполнения задания
create table task_log(
  id serial,
  id_type integer REFERENCES task_log_type(id),
  id_task BIGINT REFERENCES task(id),
  success boolean,
  error_code integer,
  comment text,
  date timestamp,
  PRIMARY KEY (id)
);

-- Значение сигналов
create table signal_value (
  id SERIAL,
  id_signal INTEGER REFERENCES signal(id) NOT NULL,
  value float NOT NULL,
  value_time timestamp,
  comment text,
  error_code integer,
  id_task integer REFERENCES task(id),
  primary key(id)
);

-- Выходные сигналы
create table output_signal(
  id integer UNIQUE NOT NULL,
  name VARCHAR(256) UNIQUE NOT NULL,
  primary key(id)
);

-- Значение выходных сигналов
create table output_signal_value(
  id serial,
  id_signal INTEGER REFERENCES output_signal(id) NOT NULL,
  value integer not null,
  value_time timestamp,
  comment text,
  error_code integer,
  id_task integer REFERENCES task(id),
  PRIMARY KEY(id)
);

-- Таблица загрузки данных из датчиков
create table signal_value_ext (
  id SERIAL,
  id_signal INTEGER REFERENCES signal(id) NOT NULL,
  calibrated integer,
  value float NOT NULL,
  value_time TIMESTAMP,
  primary key(id)
);

create table app_data (
  id integer UNIQUE NOT NULL,
  name VARCHAR(256) UNIQUE NOT NULL,
  value VARCHAR(256),
  primary key(id)
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

-- Таблица с текстовыми логами
create table log(
    id serial,
    type varchar(50),
    message text,
    date timestamp,
    primary key (id)
);
