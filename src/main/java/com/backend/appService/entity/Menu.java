package com.backend.appService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private Integer id;
    private String namaNemu;
    private Integer idKategori;
    private Long hargaSatuan;
    private String deskripsi;
    private String file;
    private Integer vol;
}
