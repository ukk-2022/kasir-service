package com.backend.appService.dao;

import com.backend.appService.entity.Nomax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NomaxDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Nomax findNomax(String flag) throws EmptyResultDataAccessException{
        String query = "select id as id, nomax as nomax, flag as flag from m_nomax where flag = :flag";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("flag", flag);

        return jdbcTemplate.queryForObject(query, parameterSource, new BeanPropertyRowMapper<>(Nomax.class));
    }

    public void update(Nomax nomax) throws DataAccessException{
        String query = "update m_nomax set nomax = :nomax where flag = :flag";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nomax", nomax.getNomax());
        parameterSource.addValue("flag", nomax.getFlag());

        jdbcTemplate.update(query, parameterSource);
    }


}
