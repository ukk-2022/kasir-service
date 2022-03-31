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
    private Long vJumlah;
    private Long vMasuk;
    private Long vKeluar;
    private String idPegawai;
    private String statusTransaksi;
    private List<DetailTransaksi> daftarMenu;
    private Date tanggal;
    private String dateToString;
}
