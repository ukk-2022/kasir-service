package com.backend.appService.service;

import com.backend.appService.dao.MenuDAO;
import com.backend.appService.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuService {

    @Autowired
    private MenuDAO dao;

    @Value("${file.path.image}")
    private String basePath;

    public void save(Menu menu)throws SQLException{
        dao.save(menu);
        Optional<Menu> data = dao.findLastTransaksi();
        menu.setId(data.get().getId());
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

    public String uploadFile(MultipartFile file){
        try{
            Path root = Paths.get(basePath);
            String[] fileFrags = file.getOriginalFilename().split("\\.");
            String extension = fileFrags[fileFrags.length - 1];
            String uuid = UUID.randomUUID().toString() + "." + extension;
            Files.copy(file.getInputStream(), root.resolve(uuid));
            return uuid;
        } catch (IOException e){
            throw new RuntimeException("could not store the file. error : " + e.getMessage());
        }
    }

    public Resource load(String fileName){
        try{
            Path root = Paths.get(basePath);
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return  resource;
            } else {
                throw  new RuntimeException("couldn't found the file");
            }
        } catch (MalformedURLException a){
            throw  new RuntimeException("Cannot show picture");
        }
    }

}
