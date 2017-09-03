create or REPLACE function processSignalValueExt()
  RETURNS TRIGGER AS $$
  declare
    idTask bigint := null;
  begin
    select t.id
    into idTask
    from task t
      join task_status ts
        on t.status = ts.id
      left join signal_value sv
        on sv.id_task = t.id
    where t.complete = false
          and ts.systemname = 'SENDTOSENSOR'
          and sv.value is null
          and t.id_signal = new.id_signal;
    if idTask is not null THEN
      insert into signal_value(id_signal, value, value_time, id_task, error_code) values(new.id_signal, new.value, current_timestamp, idTask, 0);
    END IF;
    return null;
  end;
$$ LANGUAGE plpgsql;