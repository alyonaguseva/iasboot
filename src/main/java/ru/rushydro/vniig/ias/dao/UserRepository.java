package ru.rushydro.vniig.ias.dao;

import org.springframework.data.repository.CrudRepository;
import ru.rushydro.vniig.ias.dao.entity.User;

/**
 * Created by yazik on 30.04.2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String name);

}
