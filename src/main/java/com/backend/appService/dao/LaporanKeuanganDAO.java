package com.backend.appService.dao;

import com.backend.appService.entity.LaporanKeuangan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LaporanKeuanganDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public LaporanKeuangan getData(){
        String baseQuery = "select 'Work Coffe' as namaKafe, coalesce(sum(v_jumlah), 0) as jumlahSaldoTransaksi,\n" +
                "(select count(*) from riwayat) as jumlahTransaksi, TO_CHAR(tanggal , 'YYYY') as tahunPenjualan\n" +
                "from riwayat where TO_CHAR(tanggal , 'YYYY') = TO_CHAR(current_date , 'YYYY') group by tahunpenjualan";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return jdbcTemplate.queryForObject(baseQuery, parameterSource, new BeanPropertyRowMapper<>(LaporanKeuangan.class));
    }

}
