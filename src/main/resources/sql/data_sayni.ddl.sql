-- Заполнение таблицы объекты мониторинга
insert into object_monitor VALUES (1, 'Плотина');
insert into object_monitor VALUES (2, 'ЦДП-1,2 ЦДЛ-1,2');

-- Заполнение таблицы датчиков
insert into sensor VALUES (139, 'ДУ-1', 1, 'Оптический', 1);
insert into sensor VALUES (140, 'ДУ-2', 1, 'Оптический', 1); --103
insert into sensor VALUES (141, 'ДУ-3', 1, 'Оптический', 1); --105
insert into sensor VALUES (127, 'ДУ-4', 1, 'Оптический', 1); --107
insert into sensor VALUES (128, 'ДД-5', 1, 'Оптический', 1); --109
insert into sensor VALUES (137, 'ТВ-1', 1, 'Оптический', 1); --111
insert into sensor VALUES (138, 'ТВ-2', 1, 'Оптический', 1); --112

insert into sensor VALUES (142, 'ТБ-1', 1, 'Оптический', 1); --113
insert into sensor VALUES (143, 'ТБ-2', 1, 'Оптический', 1); --114
insert into sensor VALUES (144, 'ТБ-3', 1, 'Оптический', 1); --115
insert into sensor VALUES (145, 'ТБ-4', 1, 'Оптический', 1); --116
insert into sensor VALUES (146, 'ТБ-5', 1, 'Оптический', 1); --117
insert into sensor VALUES (147, 'ТБ-6', 1, 'Оптический', 1); --118
insert into sensor VALUES (148, 'ТБ-7', 1, 'Оптический', 1); --120
insert into sensor VALUES (149, 'ТБ-8', 1, 'Оптический', 1); --121
insert into sensor VALUES (150, 'ТБ-9', 1, 'Оптический', 1); --122
insert into sensor VALUES (151, 'ТБ-10', 1, 'Оптический', 1); --123
insert into sensor VALUES (152, 'ТБ-11', 1, 'Оптический', 1); --124
insert into sensor VALUES (129, 'Щ-1', 1, 'Оптический', 1); --125
insert into sensor VALUES (130, 'Щ-2', 1, 'Оптический', 1); --127
insert into sensor VALUES (131, 'Щ-3', 1, 'Оптический', 1); --129
insert into sensor VALUES (132, 'Щ-4', 1, 'Оптический', 1); --131
insert into sensor VALUES (133, 'Щ-5', 1, 'Оптический', 1); --133
insert into sensor VALUES (134, 'Щ-6', 1, 'Оптический', 1); --135
insert into sensor VALUES (135, 'Щ-7', 1, 'Оптический', 1); --137
insert into sensor VALUES (136, 'Щ-8', 1, 'Оптический', 1); --139

insert into sensor VALUES (123, 'ВП-1', 2, 'Аналоговый', 1); --141
insert into sensor VALUES (124, 'ВП-2', 2, 'Аналоговый', 1); --142
insert into sensor VALUES (125, 'ВП-3', 2, 'Аналоговый', 1); --143
insert into sensor VALUES (126, 'ВП-4', 2, 'Аналоговый', 1); --144

insert into sensor VALUES (398, 'БП, шкаф АСДК', 1, 'Оптический', 0); --201
insert into sensor VALUES (399, 'БП, шкаф ПТК ИДС', 1, 'Оптический', 0); --202
insert into sensor VALUES (401, 'Шкаф АСДК, передняя дверь', 1, 'Оптический', 0); --203
insert into sensor VALUES (403, 'Шкаф АСДК, задняя дверь', 1, 'Оптический', 0); --204
insert into sensor VALUES (400, 'Шкаф ПТК ИДС, передняя дверь', 1, 'Оптический', 0); --205
insert into sensor VALUES (402, 'Шкаф ПТК ИДС, задняя дверь', 1, 'Оптический', 0); --206

-- Заполнение таблицы измеряемых параметров
insert into measured_parameter VALUES (1, 'ДУ - длина волны, нм (температура)',2);
insert into measured_parameter VALUES (2, 'ДУ - длина волны, нм (уровень воды)',1);
insert into measured_parameter VALUES (3, 'T воздуха - длина волны, нм',1);
insert into measured_parameter VALUES (4, 'T бетона - длина волны, нм',1);
insert into measured_parameter VALUES (5, 'Датчик линейных перемещений - длина волны, нм (раскр)',1);
insert into measured_parameter VALUES (6, 'ВП - ток (4-20 mA)',0);
insert into measured_parameter VALUES (7, 'Охранный датчик двери шкафа',0);
insert into measured_parameter VALUES (8, 'Датчик блока питания шкафа',0);
insert into measured_parameter VALUES (22, 'ДД - длина волны, нм (атм. давл)',1);
insert into measured_parameter VALUES (23, 'Датчик линейных перемещений - длина волны, нм (температура)',2);

-- old measured_parameter
-- insert into measured_parameter VALUES (1, 'Температура воды');
-- insert into measured_parameter VALUES (2, 'Уровень воды');
-- insert into measured_parameter VALUES (3, 'Температура воздуха');
-- insert into measured_parameter VALUES (4, 'Температура бетона');
-- insert into measured_parameter VALUES (5, 'Линейное перемещение');
-- insert into measured_parameter VALUES (6, 'Расход воды');
-- insert into measured_parameter VALUES (7, 'Охранный датчик двери шкафа');
-- insert into measured_parameter VALUES (8, 'Датчик блока питания шкафа');
-- insert into measured_parameter VALUES (9, 'Тензометр - длина волны, нм');
-- insert into measured_parameter VALUES (10, 'Термометр - сопротивление, Ом');

-- Заполнение таблицы видов сигналов
insert into signal_type VALUES (1, 'Аналоговый (Оптический)');
insert into signal_type VALUES (2, '(4-20мА)');

-- Заполнение таблицы сигналов
insert into signal VALUES (101, 139, 1, 1);
insert into signal VALUES (102, 139, 1, 2);
insert into signal VALUES (103, 140, 1, 1);
insert into signal VALUES (104, 140, 1, 2);
insert into signal VALUES (105, 141, 1, 1);
insert into signal VALUES (106, 141, 1, 2);
insert into signal VALUES (107, 127, 1, 1);
insert into signal VALUES (108, 127, 1, 2);

insert into signal VALUES (109, 128, 1, 22);

insert into signal VALUES (111, 137, 1, 3);
insert into signal VALUES (112, 138, 1, 3);

insert into signal VALUES (113, 142, 1, 4);
insert into signal VALUES (114, 143, 1, 4);
insert into signal VALUES (115, 144, 1, 4);
insert into signal VALUES (116, 145, 1, 4);
insert into signal VALUES (117, 146, 1, 4);
insert into signal VALUES (118, 147, 1, 4);
insert into signal VALUES (120, 148, 1, 4);
insert into signal VALUES (121, 149, 1, 4);
insert into signal VALUES (122, 150, 1, 4);
insert into signal VALUES (123, 151, 1, 4);
insert into signal VALUES (124, 152, 1, 4);

insert into signal VALUES (125, 129, 1, 5);
insert into signal VALUES (126, 129, 1, 23);
insert into signal VALUES (127, 130, 1, 5);
insert into signal VALUES (128, 130, 1, 23);
insert into signal VALUES (129, 131, 1, 5);
insert into signal VALUES (130, 131, 1, 23);
insert into signal VALUES (131, 132, 1, 5);
insert into signal VALUES (132, 132, 1, 23);
insert into signal VALUES (133, 133, 1, 5);
insert into signal VALUES (134, 133, 1, 23);
insert into signal VALUES (135, 134, 1, 5);
insert into signal VALUES (136, 134, 1, 23);
insert into signal VALUES (137, 135, 1, 5);
insert into signal VALUES (138, 135, 1, 23);
insert into signal VALUES (139, 136, 1, 5);
insert into signal VALUES (140, 136, 1, 23);

insert into signal VALUES (141, 123, 2, 6);
insert into signal VALUES (142, 124, 2, 6);
insert into signal VALUES (143, 125, 2, 6);
insert into signal VALUES (144, 126, 2, 6);

insert into signal VALUES (201, 398, 1, 8);
insert into signal VALUES (202, 399, 1, 8);
insert into signal VALUES (203, 401, 1, 7);
insert into signal VALUES (204, 403, 1, 7);
insert into signal VALUES (205, 400, 1, 7);
insert into signal VALUES (206, 402, 1, 7);

-- Заполнение таблицы выходных сигналов
insert into output_signal VALUES (1, 'Превышение уровня К1');
insert into output_signal VALUES (2, 'Превышение уровня К2');
insert into output_signal VALUES (3, 'Электропитание шкафа АСДК ГТС (220 В, 50 Гц) отсутствует/присутствует');
insert into output_signal VALUES (4, 'Открытие/закрытие передней двери шкафа АСДК ГТС');
insert into output_signal VALUES (5, 'Открытие/закрытие задней двери шкафа АСДК ГТС');
insert into output_signal VALUES (6, 'Электропитание шкафа ПТК ИДС (220 В, 50 Гц) отсутствует/присутствует');
insert into output_signal VALUES (7, 'Открытие/закрытие передней двери шкафа ПТК ИДС');
insert into output_signal VALUES (8, 'Открытие/закрытие задней двери ПТК ИДС');

