package com.backend.appService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaksi {

    private Long idTransaksi;
    private Long uangJumlah;
    private Long uangMasuk;
    private Long uangKeluar;
    private String idPegawai;
    private String namaPegawai;
    private String statusTransaksi;
    private List<DetailTransaksi> daftarMenu;
    private Date tanggal;
    private String dateToString;
}
