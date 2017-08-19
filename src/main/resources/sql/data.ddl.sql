-- Добавляем пользователя admin - admin
insert into users(username, password) values('admin', '21232f297a57a5a743894a0e4a801fc3');

-- Заполнение таблицы объекты мониторинга
insert into object_monitor VALUES (1, 'Плотина');
insert into object_monitor VALUES (2, 'ЦДП-1,2 ЦДЛ-1,2');

-- Заполнение таблицы датчиков
insert into sensor VALUES (101, 'ДУ-1', 1, 'Оптический', 1);
insert into sensor VALUES (102, 'ДУ-2', 1, 'Оптический', 1);
insert into sensor VALUES (103, 'ДУ-3', 1, 'Оптический', 1);
insert into sensor VALUES (104, 'ДУ-4', 1, 'Оптический', 1);
insert into sensor VALUES (105, 'ДУ-5', 1, 'Оптический', 1);
insert into sensor VALUES (106, 'ТВ-1', 1, 'Оптический', 1);
insert into sensor VALUES (107, 'ТВ-2', 1, 'Оптический', 1);

insert into sensor VALUES (108, 'ТБ-1', 1, 'Оптический', 1);
insert into sensor VALUES (109, 'ТБ-2', 1, 'Оптический', 1);
insert into sensor VALUES (110, 'ТБ-3', 1, 'Оптический', 1);
insert into sensor VALUES (111, 'ТБ-4', 1, 'Оптический', 1);
insert into sensor VALUES (112, 'ТБ-5', 1, 'Оптический', 1);
insert into sensor VALUES (113, 'ТБ-6', 1, 'Оптический', 1);
insert into sensor VALUES (114, 'ТБ-7', 1, 'Оптический', 1);
insert into sensor VALUES (115, 'ТБ-8', 1, 'Оптический', 1);
insert into sensor VALUES (116, 'ТБ-9', 1, 'Оптический', 1);
insert into sensor VALUES (117, 'ТБ-10', 1, 'Оптический', 1);
insert into sensor VALUES (118, 'ТБ-11', 1, 'Оптический', 1);
insert into sensor VALUES (120, 'Щ-1', 1, 'Оптический', 1);
insert into sensor VALUES (121, 'Щ-2', 1, 'Оптический', 1);
insert into sensor VALUES (122, 'Щ-3', 1, 'Оптический', 1);
insert into sensor VALUES (123, 'Щ-4', 1, 'Оптический', 1);
insert into sensor VALUES (124, 'Щ-5', 1, 'Оптический', 1);
insert into sensor VALUES (125, 'Щ-6', 1, 'Оптический', 1);
insert into sensor VALUES (126, 'Щ-7', 1, 'Оптический', 1);
insert into sensor VALUES (127, 'Щ-8', 1, 'Оптический', 1);

insert into sensor VALUES (128, 'ВП-1', 2, 'Аналоговый', 1);
insert into sensor VALUES (129, 'ВП-2', 2, 'Аналоговый', 1);
insert into sensor VALUES (130, 'ВП-3', 2, 'Аналоговый', 1);
insert into sensor VALUES (131, 'ВП-4', 2, 'Аналоговый', 1);

insert into sensor VALUES (132, 'Щ-2', 1, 'Оптический', 1);
insert into sensor VALUES (133, 'Щ-3', 1, 'Оптический', 1);
insert into sensor VALUES (134, 'Щ-4', 1, 'Оптический', 1);
insert into sensor VALUES (135, 'Щ-5', 1, 'Оптический', 1);
insert into sensor VALUES (136, 'Щ-6', 1, 'Оптический', 1);
insert into sensor VALUES (201, 'Щ-7', 1, 'Оптический', 1);
insert into sensor VALUES (202, 'Щ-8', 1, 'Оптический', 1);

insert into sensor VALUES (203, 'ВП-1', 2, 'Аналоговый', 1);
insert into sensor VALUES (204, 'ВП-2', 2, 'Аналоговый', 1);
insert into sensor VALUES (205, 'ВП-3', 2, 'Аналоговый', 1);
insert into sensor VALUES (206, 'ВП-4', 2, 'Аналоговый', 1);

-- Заполнение таблицы измеряемых параметров
insert into measured_parameter VALUES (1, 'Температура воды');
insert into measured_parameter VALUES (2, 'Уровень воды');
insert into measured_parameter VALUES (3, 'Температура воздуха');
insert into measured_parameter VALUES (4, 'Температура бетона');
insert into measured_parameter VALUES (5, 'Линейное перемещение');
insert into measured_parameter VALUES (6, 'Расход воды');
insert into measured_parameter VALUES (7, 'Охранный датчик двери шкафа');
insert into measured_parameter VALUES (8, 'Датчик блока питания шкафа');
insert into measured_parameter VALUES (9, 'Тензометр - длина волны, нм');
insert into measured_parameter VALUES (10, 'Термометр - сопротивление, Ом');

-- Заполнение таблицы видов сигналов
insert into signal_type VALUES (1, 'Аналоговый (Оптический)');
insert into signal_type VALUES (2, '(4-20мА)');

-- Заполнение таблицы сигналов
insert into signal VALUES (1, 101, 1, 9);
insert into signal VALUES (2, 101, 1, 8);
insert into signal VALUES (3, 102, 1, 9);
insert into signal VALUES (4, 102, 1, 8);
insert into signal VALUES (5, 103, 1, 9);
insert into signal VALUES (6, 103, 1, 8);
insert into signal VALUES (7, 104, 1, 9);
insert into signal VALUES (8, 104, 1, 8);
insert into signal VALUES (9, 105, 1, 9);
insert into signal VALUES (10, 105, 1, 8);

insert into signal VALUES (11, 106, 1, 8);
insert into signal VALUES (12, 107, 1, 9);

insert into signal VALUES (13, 108, 1, 8);
insert into signal VALUES (14, 109, 1, 9);
insert into signal VALUES (15, 110, 1, 8);
insert into signal VALUES (16, 111, 1, 3);
insert into signal VALUES (17, 112, 1, 3);
insert into signal VALUES (18, 113, 1, 4);
insert into signal VALUES (19, 114, 1, 4);
insert into signal VALUES (20, 115, 1, 4);
insert into signal VALUES (21, 116, 1, 4);
insert into signal VALUES (22, 117, 1, 4);
insert into signal VALUES (23, 118, 1, 4);

insert into signal VALUES (24, 120, 1, 4);
insert into signal VALUES (25, 121, 1, 4);
insert into signal VALUES (26, 122, 1, 4);
insert into signal VALUES (27, 123, 1, 4);
insert into signal VALUES (28, 124, 1, 4);
insert into signal VALUES (29, 125, 1, 5);
insert into signal VALUES (30, 126, 1, 5);
insert into signal VALUES (31, 127, 1, 5);

insert into signal VALUES (32, 128, 2, 5);
insert into signal VALUES (33, 129, 2, 5);
insert into signal VALUES (34, 130, 2, 5);
insert into signal VALUES (35, 131, 2, 5);

insert into signal VALUES (36, 132, 2, 5);
insert into signal VALUES (37, 133, 2, 6);
insert into signal VALUES (38, 134, 2, 6);
insert into signal VALUES (39, 135, 2, 6);
insert into signal VALUES (40, 136, 2, 6);

insert into signal VALUES (41, 201, 2, 8);
insert into signal VALUES (42, 202, 2, 8);
insert into signal VALUES (43, 203, 2, 7);
insert into signal VALUES (44, 204, 2, 7);
insert into signal VALUES (45, 205, 2, 7);
insert into signal VALUES (46, 206, 2, 7);

-- Заполнение таблицы выходных сигналов
insert into output_signal VALUES (1, 'Превышение уровня К1');
insert into output_signal VALUES (2, 'Превышение уровня К2');
insert into output_signal VALUES (3, 'Электропитание шкафа АСДК ГТС (220 В, 50 Гц) отсутствует/присутствует');
insert into output_signal VALUES (4, 'Открытие/закрытие передней двери шкафа АСДК ГТС');
insert into output_signal VALUES (5, 'Открытие/закрытие задней двери шкафа АСДК ГТС');
insert into output_signal VALUES (6, 'Электропитание шкафа ПТК ИДС (220 В, 50 Гц) отсутствует/присутствует');
insert into output_signal VALUES (7, 'Открытие/закрытие передней двери шкафа ПТК ИДС');
insert into output_signal VALUES (8, 'Открытие/закрытие задней двери ПТК ИДС');

insert into task_status VALUES (1, 'Новое задание', 'NEW');
insert into task_status VALUES (2, 'Отправлено на выполнение', 'SENDTOSENSOR');
insert into task_status VALUES (3, 'Ожидает отправки в буферную зону', 'NEEDTOSEND');
insert into task_status VALUES (4, 'Выполнено', 'COMPLETE');

insert into task_log_type VALUES (1, 'Новое задание', 'NEW');
insert into task_log_type VALUES (2, 'Отправлено на выполнение', 'SENDTOSENSOR');
insert into task_log_type VALUES (3, 'Ожидает отправки в буферную зону', 'NEEDTOSEND');
insert into task_log_type VALUES (4, 'Выполнено', 'COMPLETE');