-- Объекты мониторинга
create table mpf_table (
   id integer UNIQUE NOT NULL,
   commstring varchar(512),
   address integer,
   di1 varchar(16),
   di2 varchar(16),
   primary key(id)
);

create table mtm_table (
  mpf_id integer REFERENCES mpf_table(id) NOT NULL not null,
  mtm_pos integer not null,
  ch1 varchar(32),
  ch2 varchar(32),
  ch3 varchar(32),
  ch4 varchar(32),
  ch5 varchar(32),
  ch6 varchar(32),
  ch7 varchar(32),
  ch8 varchar(32),
  ch9 varchar(32),
  ch10 varchar(32),
  ch11 varchar(32),
  ch12 varchar(32),
  ch13 varchar(32),
  ch14 varchar(32),
  ch15 varchar(32),
  ch16 varchar(32),
  primary key (mpf_id, mtm_pos)
);