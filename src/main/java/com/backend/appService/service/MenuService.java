package com.backend.appService.service;

import com.backend.appService.dao.MenuDAO;
import com.backend.appService.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuDAO dao;

    public void save(Menu menu)throws SQLException{
        dao.save(menu);
        dao.newRepo(menu);
    }

    public void tambahStok(Menu menu) throws SQLException {
        dao.tambahStok(menu);
    }

    public List<Menu> findAll(){
        return dao.findAll();
    }

    public List<Menu> findByKategori(Integer idKategori){
        return dao.findByKategori(idKategori);
    }

    public void delete(Integer id){
        dao.delete(id);
    }

}
