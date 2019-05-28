-- Добавляем пользователя admin - admin
insert into users(username, password) values('admin', '21232f297a57a5a743894a0e4a801fc3');

insert into task_status VALUES (1, 'Новое задание', 'NEW');
insert into task_status VALUES (2, 'Отправлено на выполнение', 'SENDTOSENSOR');
insert into task_status VALUES (3, 'Ожидает отправки в буферную зону', 'NEEDTOSEND');
insert into task_status VALUES (4, 'Выполнено', 'COMPLETE');
insert into task_status VALUES (5, 'Просрочено', 'OVERDUE');

insert into task_log_type VALUES (1, 'Новое задание', 'NEW');
insert into task_log_type VALUES (2, 'Отправлено на выполнение', 'SENDTOSENSOR');
insert into task_log_type VALUES (3, 'Ожидает отправки в буферную зону', 'NEEDTOSEND');
insert into task_log_type VALUES (4, 'Выполнено', 'COMPLETE');
insert into task_log_type VALUES (5, 'Просрочено', 'OVERDUE');

insert into app_data values(1, 'lastParseRow', '0');

insert into versions values(1, 'db', '6');