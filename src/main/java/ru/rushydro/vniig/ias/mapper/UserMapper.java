package ru.rushydro.vniig.ias.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.rushydro.vniig.ias.dao.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yazik on 30.04.2017.
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setUsername(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        return user;
    }
}
