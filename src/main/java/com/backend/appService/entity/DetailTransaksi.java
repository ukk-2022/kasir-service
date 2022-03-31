package com.backend.appService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailTransaksi {

    private Integer id;
    private Long idTransaksi;
    private Integer idMenu;
    private Integer volume;
}
