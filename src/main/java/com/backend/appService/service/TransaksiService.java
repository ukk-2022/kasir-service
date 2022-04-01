package com.backend.appService.service;

import com.backend.appService.dao.NomaxDAO;
import com.backend.appService.dao.TransaksiDAO;
import com.backend.appService.entity.DetailTransaksi;
import com.backend.appService.entity.Nomax;
import com.backend.appService.entity.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiDAO dao;

    @Autowired
    private NomaxDAO nomaxDAO;

    public Transaksi newTransaksi(Transaksi value){
        Transaksi transaksi = new Transaksi();
        Nomax nomax = nomaxDAO.findNomax("transaksi");
        value.setIdTransaksi(nomax.getNomax());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        value.setTanggal(date);
        dao.saveTransaksi(value);
        for (DetailTransaksi data : value.getDaftarMenu()){
            data.setIdTransaksi(nomax.getNomax());
            dao.saveDetailTransaksi(data);
        }
        nomax.setNomax(nomax.getNomax()+1);
        nomaxDAO.update(nomax);
        return value;
    }

}
