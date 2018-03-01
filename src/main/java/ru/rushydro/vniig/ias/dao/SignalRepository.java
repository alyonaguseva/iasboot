package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.MeasuredParameter;
import ru.rushydro.vniig.ias.dao.entity.Sensor;
import ru.rushydro.vniig.ias.dao.entity.Signal;

/**
 * Created by yazik on 30.04.2017.
 */
public interface SignalRepository extends CrudRepository<Signal, Integer> {

    Signal findBySensorAndMeasuredParameter(Sensor sensor,
                                            MeasuredParameter measuredParameter);

    Signal findBySensor(Sensor sensor);

}
