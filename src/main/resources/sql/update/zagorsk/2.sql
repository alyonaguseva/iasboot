update signal set id_measured_parameter = 10 where id in (1, 2, 3);

update signal set id_sensor = 61 where id = 61;
update signal set id_sensor = 62 where id = 62;

update signal set id_measured_parameter = 2 where id BETWEEN 16 and 63;
update signal set id_measured_parameter = 3 where id BETWEEN 64 and 87;
