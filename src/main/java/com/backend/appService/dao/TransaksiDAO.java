package com.backend.appService.dao;

import com.backend.appService.entity.DetailTransaksi;
import com.backend.appService.entity.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransaksiDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void saveTransaksi(Transaksi value){
        String baseQuery = "insert into riwayat(id_transaksi, v_jumlah, v_masuk, v_keluar, id_pergawai, status_transaksi, tanggal)\n" +
                "values((select nomax as nomax from m_nomax where flag = 'transaksi'), :vJumlah, :vMasuk, :vKeluar, \n" +
                ":idPegawai, 'transaksi masuk', :tanggal)";

        System.out.println(value);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vJumlah", value.getUangJumlah());
        parameterSource.addValue("vMasuk", value.getUangMasuk());
        parameterSource.addValue("vKeluar", value.getUangKeluar());
        parameterSource.addValue("idPegawai", value.getIdPegawai());
        parameterSource.addValue("tanggal", value.getTanggal());

        jdbcTemplate.update(baseQuery, parameterSource);
    }

    public void saveDetailTransaksi(DetailTransaksi detailTransaksi){
        String baseQuery = "insert into detail_transaksi(id, id_transaksi, id_menu, vol)\n" +
                "values((select nomax as nomax from m_nomax where flag = 'detail_transaksi'), :idTransaksi, :idMenu, :vol)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idTransaksi", detailTransaksi.getIdTransaksi());
        parameterSource.addValue("idMenu", detailTransaksi.getIdMenu());
        parameterSource.addValue("vol", detailTransaksi.getVolume());

        jdbcTemplate.update(baseQuery, parameterSource);
    }

    public Transaksi findLastTransaksi() throws EmptyResultDataAccessException{
        String query = "select r.id_transaksi as idTransaksi,\n" +
                "r.v_jumlah as uangJumlah,\n" +
                "r.v_masuk as uangMasuk,\n" +
                "r.v_keluar as uangKeluar,\n" +
                "r.id_pergawai as idPegawai,\n" +
                "u.username as namaPegawai,\n" +
                "TO_CHAR(r.tanggal , 'DD-MM-YYYY') as dateToString\n" +
                "from riwayat r join \"user\" u on r.id_pergawai = u.id \n" +
                "order by r.id_transaksi desc limit 1";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return jdbcTemplate.queryForObject(query, parameterSource, new BeanPropertyRowMapper<>(Transaksi.class));
    }

    public List<DetailTransaksi> findDetailTransaksi(Long idTransaksi) throws DataAccessException{
        String query = "select dt.id as id,\n" +
                "dt.id_transaksi as idTransaksi,\n" +
                "dt.id_menu as idMenu,\n" +
                "dt.vol as volume,\n" +
                "m.nama_menu as namaMenu,\n" +
                "m.v_harga_satuan as hargaSatuan\n" +
                "from detail_transaksi dt join menu m on dt.id_menu = m.id\n" +
                "where dt.id_transaksi = :idTransaksi";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idTransaksi", idTransaksi);

        return jdbcTemplate.query(query, parameterSource, new BeanPropertyRowMapper<>(DetailTransaksi.class));
    }

    public void updateNomax(){
        String baseQuery = "update m_nomax set nomax = (select nomax as nomax from m_nomax where flag = 'detail_transaksi')+1 " +
                "where flag = 'detail_transaksi'";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        jdbcTemplate.update(baseQuery, parameterSource);
    }

}
