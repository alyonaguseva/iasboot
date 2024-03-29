create or REPLACE function processSignalValueExt()
  RETURNS TRIGGER AS $$
  declare
    idTask bigint := null;
    nCount bigint := 0;
  begin
    select count(*)
    into nCount
    from signal s join
      measured_parameter mp
        on s.id_measured_parameter = mp.id
    where s.id = new.id_signal and ((mp.data_type = 0 and new.calibrated = 1)
                                    or (mp.data_type > 0 and new.calibrated = 0));
    if nCount > 0 then
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
    END IF;
    return null;
  end;
$$ LANGUAGE plpgsql;