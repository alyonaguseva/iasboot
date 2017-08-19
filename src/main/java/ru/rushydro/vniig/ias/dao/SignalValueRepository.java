package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.OutputSignalValue;
import ru.rushydro.vniig.ias.dao.entity.SignalValue;
import ru.rushydro.vniig.ias.dao.entity.Task;

/**
 * Created by yazik on 30.04.2017.
 */
public interface SignalValueRepository extends CrudRepository<SignalValue, Long> {

    SignalValue findByTask(Task task);

}
