package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import ru.rushydro.vniig.ias.dao.entity.Task;
import ru.rushydro.vniig.ias.dao.entity.TaskLog;

import java.util.List;

/**
 * Created by yazik on 30.04.2017.
 */
public interface TaskLogRepository extends PagingAndSortingRepository<TaskLog, Long> {

//    List<Task> findByCompleteFalse();

}
