package com.backend.appService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaporanKeuangan {

    private String namaKafe;
    private Long jumlahSaldoTransaksi;
    private Integer jumlahTransaksi;
    private String tahunPenjualan;
}
