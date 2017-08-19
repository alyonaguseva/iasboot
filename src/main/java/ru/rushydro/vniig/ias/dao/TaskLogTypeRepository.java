package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.TaskLogType;

/**
 * Created by yazik on 30.04.2017.
 */
public interface TaskLogTypeRepository extends CrudRepository<TaskLogType, Integer> {

    TaskLogType findBySystemname(String name);

}
