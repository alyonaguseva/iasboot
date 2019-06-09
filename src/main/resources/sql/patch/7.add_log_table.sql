update versions set version = '7' where name = 'db';

create table log(
    id serial,
    type varchar(50),
    message text,
    date timestamp,
    primary key (id)
);