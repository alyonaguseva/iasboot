create table sensor_setting (
    id integer REFERENCES sensor(id) NOT NULL,
    tag_name varchar(128) NOT NULL,
    primary key(id));