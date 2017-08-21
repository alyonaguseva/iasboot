package ru.rushydro.vniig.ias.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import ru.rushydro.vniig.ias.dao.entity.Task;
import ru.rushydro.vniig.ias.dao.entity.TaskLog;

import java.util.List;

/**
 * Created by yazik on 30.04.2017.
 */
public interface TaskLogRepository extends JpaRepository<TaskLog, Long>, JpaSpecificationExecutor<TaskLog> {

    Page<TaskLog> findByOrderByIdDesc(Pageable pageable);

}
