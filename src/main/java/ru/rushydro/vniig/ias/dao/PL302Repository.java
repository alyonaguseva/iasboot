package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.MeasuredParameter;
import ru.rushydro.vniig.ias.dao.entity.Pl302;

/**
 * Created by yazik on 30.04.2017.
 */
public interface PL302Repository extends CrudRepository<Pl302, Integer> {
}
