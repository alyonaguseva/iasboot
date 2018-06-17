package ru.rushydro.vniig.ias.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rushydro.vniig.ias.dao.entity.Signal;
import ru.rushydro.vniig.ias.dao.entity.Task;

import java.time.LocalDateTime;

/**
 * Created by yazik on 30.04.2017.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByDateAndSignal(final LocalDateTime date, final Signal signal);

    Page<Task> findByCompleteFalseOrderByIdDesc(Pageable pageable);
}
