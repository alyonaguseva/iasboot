package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.TaskStatus;

/**
 * Created by yazik on 30.04.2017.
 */
public interface TaskStatusRepository extends CrudRepository<TaskStatus, Integer> {

    TaskStatus findBySystemname(String name);
}
