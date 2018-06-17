drop trigger signal_value_insert on signal_value_ext;

alter table signal_value_ext add value_time TIMESTAMP;

insert into task_status VALUES (5, 'Просрочено', 'OVERDUE');
insert into task_log_type VALUES (5, 'Просрочено', 'OVERDUE');

update sensor_value_ext set value_time = current_time where value_time is null;