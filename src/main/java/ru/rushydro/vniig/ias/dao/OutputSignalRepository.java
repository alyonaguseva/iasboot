package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.OutputSignal;

/**
 * Created by yazik on 30.04.2017.
 */
public interface OutputSignalRepository extends CrudRepository<OutputSignal, Integer> {
}
