package ru.rushydro.vniig.ias.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.rushydro.vniig.ias.dao.entity.Log;
import ru.rushydro.vniig.ias.dao.entity.TaskLog;

/**
 * Created by yazik on 30.04.2017.
 */
public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {

    Page<Log> findByOrderByIdDesc(Pageable pageable);

}
