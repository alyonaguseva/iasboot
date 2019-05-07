package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.Sensor;

/**
 * Created by yazik on 30.04.2017.
 */
public interface SensorRepository extends CrudRepository<Sensor, Integer> {
//    Iterable<Sensor> findByInTag(Boolean tag);
}
