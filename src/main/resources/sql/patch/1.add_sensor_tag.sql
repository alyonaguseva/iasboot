alter table sensor add column in_tag BOOLEAN;

update sensor set in_tag = true where id in(123, 124, 125, 126);

insert into measured_parameter VALUES (37, 'Расход воды через расходомеры в литрах в секунду', 0);

update signal set id_measured_parameter = 37 where id in(141, 142, 143, 144);