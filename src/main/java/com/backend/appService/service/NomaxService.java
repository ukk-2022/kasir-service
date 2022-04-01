package com.backend.appService.service;

import com.backend.appService.dao.NomaxDAO;
import com.backend.appService.entity.Nomax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NomaxService {

    @Autowired
    private NomaxDAO dao;

    public Nomax findNomax(String flag){
        return dao.findNomax(flag);
    }

    public void update(Nomax nomax){
        dao.update(nomax);
    }

}
