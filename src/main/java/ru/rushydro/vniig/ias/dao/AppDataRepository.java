package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.AppData;

import java.util.Optional;

/**
 * Created by yazik on 04.11.2017.
 */
public interface AppDataRepository extends CrudRepository<AppData, Integer> {

    Optional<AppData> findByName(String name);

}
