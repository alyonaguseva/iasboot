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
  primary key(id)
);

create table app_data (
  id integer UNIQUE NOT NULL,
  name VARCHAR(256) UNIQUE NOT NULL,
  value VARCHAR(256),
  primary key(id)
);
