package com.backend.appService.dao;

import com.backend.appService.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MenuDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Menu> findAll() throws DataAccessException{
        String baseQuery = "select m.id as id, m.nama_menu as namaMenu, m.id_kategori as idKategori, " +
                "m.v_harga_satuan as hargaSatuan, m.deskripsi as deskripsi, m.file as file, r.stok as vol from menu m left join " +
                "repo r on m.id = r.id_menu";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return jdbcTemplate.query(baseQuery, parameterSource, new BeanPropertyRowMapper<>(Menu.class));
    }

    public Optional<Menu> findLastTransaksi() throws DataAccessException{
        String baseQuery = "select m.id as id, m.nama_menu as namaMenu, m.id_kategori as idKategori, " +
                "m.v_harga_satuan as hargaSatuan, m.deskripsi as deskripsi, m.file as file, r.stok as vol from menu m left join " +
                "repo r on m.id = r.id_menu order by id desc limit 1";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        Menu data = jdbcTemplate.queryForObject(baseQuery, parameterSource, new BeanPropertyRowMapper<>(Menu.class));

        return Optional.of(data);
    }

    public List<Menu> findByKategori(Integer idKategori) throws DataAccessException{
        String baseQuery = "select m.id as id, m.nama_menu as namaMenu, m.id_kategori as idKategori, " +
                "m.v_harga_satuan as hargaSatuan, m.deskripsi as deskripsi, m.file as file, r.stok as vol from menu m left join " +
                "repo r on m.id = r.id_menu where m.id_kategori = :idKategori";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idKategori", idKategori);

        return jdbcTemplate.query(baseQuery, parameterSource, new BeanPropertyRowMapper<>(Menu.class));
    }

    public void save(Menu menu) throws SQLException{
        String query = "insert into menu(nama_menu, id_kategori, v_harga_satuan, deskripsi, file)\n" +
                "values(:namaMenu, :idKategori, :hargaSatuan, :deskripsi, :file)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("namaMenu", menu.getNamaNemu());
        parameterSource.addValue("idKategori", menu.getIdKategori());
        parameterSource.addValue("hargaSatuan", menu.getHargaSatuan());
        parameterSource.addValue("deskripsi", menu.getDeskripsi());
        parameterSource.addValue("file", menu.getFile());

        jdbcTemplate.update(query, parameterSource);
    }

    public void newRepo(Menu menu) throws SQLException{
        String query = "insert into repo(id_menu, stok)\n" +
                "values(:idMenu, :stok)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idMenu", menu.getId());
        parameterSource.addValue("stok", menu.getVol());

        jdbcTemplate.update(query, parameterSource);
    }

    public void tambahStok(Menu menu)throws SQLException{
        String query = "update repo set stok = :stok where id_menu = :idMenu";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("stok", menu.getVol());
        parameterSource.addValue("idMenu", menu.getId());

        jdbcTemplate.update(query, parameterSource);
    }

    public void delete(Integer id){
        String query = "delete from menu where id = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        jdbcTemplate.update(query, parameterSource);
    }

}
