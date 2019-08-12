update measured_parameter set name = 'Глубина инклинометра' where id = 5;
update measured_parameter set name = 'Угол по оси X' where id = 6;
update measured_parameter set name = 'Угол по оси Y' where id = 7;
update measured_parameter set name = 'Изменение угла относительно нулевого значения по оси X' where id = 8;
update measured_parameter set name = 'Изменение угла относительно нулевого значения по оси Y' where id = 9;

insert into measured_parameter values(11, 'Смещение инклинометра относительно нулевого значения по оси X', 0);
insert into measured_parameter values(12, 'Смещение инклинометра относительно нулевого значения по оси Y', 0);
insert into measured_parameter values(13, 'Локальная координата текущего инклинометра относительно начального по оси X', 0);
insert into measured_parameter values(14, 'Локальная координата текущего инклинометра относительно начального по оси Y', 0);

create sequence signal_id_seq start with 258;

insert into signal(id, id_sensor, id_type, id_measured_parameter)
select nextval('signal_id_seq'), id_sensor, 1, 11 from signal where id_measured_parameter = 5;
insert into signal(id, id_sensor, id_type, id_measured_parameter)
select nextval('signal_id_seq'), id_sensor, 1, 12 from signal where id_measured_parameter = 5;
insert into signal(id, id_sensor, id_type, id_measured_parameter)
select nextval('signal_id_seq'), id_sensor, 1, 13 from signal where id_measured_parameter = 5;
insert into signal(id, id_sensor, id_type, id_measured_parameter)
select nextval('signal_id_seq'), id_sensor, 1, 14 from signal where id_measured_parameter = 5;