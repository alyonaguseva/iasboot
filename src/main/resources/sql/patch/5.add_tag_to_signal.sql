-- Настройки сигналов
create table signal_setting (
    id integer REFERENCES signal(id) NOT NULL,
    tag_name varchar(128) NOT NULL,
    id_pl302 integer REFERENCES pl302(id),
    primary key(id)
);

alter table signal add column in_tag BOOLEAN;