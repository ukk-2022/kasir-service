package com.backend.appService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Master {

    private Integer id;
    private String keterangan;
    private String flag;
}
