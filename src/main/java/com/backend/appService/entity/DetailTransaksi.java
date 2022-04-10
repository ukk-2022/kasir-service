package com.backend.appService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailTransaksi {

    private Long id;
    private Long idTransaksi;
    private Integer idMenu;
    private Integer volume;
    private String namaMenu;
    private Long hargaSatuan;
}
