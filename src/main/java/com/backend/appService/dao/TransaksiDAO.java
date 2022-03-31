package com.backend.appService.dao;

import com.backend.appService.entity.DetailTransaksi;
import com.backend.appService.entity.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransaksiDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void saveTransaksi(Transaksi value){
        String baseQuery = "insert into riwayat(id_transaksi, v_jumlah, v_masuk, v_keluar, id_pergawai, status_transaksi, tanggal)\n" +
                "values(select nomax as nomax from m_nomax where flag = 'transaksi', :vJumlah, :vMasuk, :vKeluar, \n" +
                ":idPegawai, 'transaksi masuk', :tanggal)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vJumlah", value.getVJumlah());
        parameterSource.addValue("vMasuk", value.getVMasuk());
        parameterSource.addValue("vKeluar", value.getVKeluar());
        parameterSource.addValue("idPegawai", value.getIdPegawai());
        parameterSource.addValue("tanggal", value.getTanggal());

        jdbcTemplate.update(baseQuery, parameterSource);
    }

    public void saveDetailTransaksi(DetailTransaksi detailTransaksi){
        String baseQuery = "insert into detail_transaksi(id, id_transaksi, id_menu, vol)\n" +
                "values(select nomax as nomax from m_nomax where flag = 'detail_transaksi', :idTransaksi, idMenu, :vol)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idTransaksi", detailTransaksi.getIdTransaksi());
        parameterSource.addValue("idMenu", detailTransaksi.getIdMenu());
        parameterSource.addValue("vol", detailTransaksi.getVolume());

        jdbcTemplate.update(baseQuery, parameterSource);
    }

}
