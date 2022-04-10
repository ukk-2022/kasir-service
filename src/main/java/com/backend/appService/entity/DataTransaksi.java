package com.backend.appService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTransaksi {
    private Long idTransaksi;
    private Long uangJumlah;
    private String idPegawai;
    private String namaPegawai;
    private String dateToString;
    private Date tanggal;

}
