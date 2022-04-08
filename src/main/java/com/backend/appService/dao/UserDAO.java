package com.backend.appService.dao;

import com.backend.appService.auth.model.User;
import com.backend.appService.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void save(User user) throws SQLException{
        String baseQuery = "insert into \"user\" (id, username, \"password\", id_role)\n" +
                "values(:id, :username, :password, :idRole)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", user.getId());
        parameterSource.addValue("username", user.getUsername());
        parameterSource.addValue("password", user.getPassword());
        parameterSource.addValue("idRole", user.getIdRole());

        jdbcTemplate.update(baseQuery, parameterSource);
    }

    public List<Master> findAllRole() throws DataAccessException{
        String baseQuery = "select * from master where flag = 'role'";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return jdbcTemplate.query(baseQuery, parameterSource, new BeanPropertyRowMapper<>(Master.class));
    }
}
