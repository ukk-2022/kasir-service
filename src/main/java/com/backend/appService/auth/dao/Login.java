package com.backend.appService.auth.dao;

import com.backend.appService.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class Login {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public User getUserByUsername(String username){
        String baseQuery = "select id, id_role as idRole, username, password from \"user\" where username = :username";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);

        User dataNasabah = namedParameterJdbcTemplate.queryForObject(baseQuery, parameterSource, new BeanPropertyRowMapper<>(User.class));

        return dataNasabah;
    }

    public void addInfo(User user){
        String baseQuery= "INSERT INTO login_info(token, id_user) " +
                "VALUES(:token, :idUser)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idUser", user.getId());
        parameterSource.addValue("token", user.getTokenKey());

        namedParameterJdbcTemplate.update(baseQuery, parameterSource);
    }

    public User getNipByToken(String token){
        String baseQuery = "SELECT token as token, id_user as id from login_info = :token";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("token", token);

        User data = namedParameterJdbcTemplate.queryForObject(baseQuery, parameterSource, new BeanPropertyRowMapper<>(User.class));

        return data;
    }

    public void logout(String token){
        String baseQuery="DELETE FROM login_info where token = :token";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("token", token);

        namedParameterJdbcTemplate.update(baseQuery, parameterSource);
    }
}
