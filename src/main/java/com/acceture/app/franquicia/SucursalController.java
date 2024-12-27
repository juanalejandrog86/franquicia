package com.acceture.app.franquicia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acceture.app.entity.Franquicia;
import com.acceture.app.entity.Sucursal;
import com.acceture.app.repository.FranquiciaRepository;
import com.acceture.app.repository.SucursalRepository;

@RestController
public class SucursalController {

    private static final Logger log = LoggerFactory.getLogger(SucursalController.class);

    private final SucursalRepository repository;

    private final FranquiciaRepository franquiciaRepository;

    SucursalController(SucursalRepository repository, FranquiciaRepository franquiciaRepository) {
        this.repository = repository;
        this.franquiciaRepository = franquiciaRepository;
    }

    @GetMapping("/sucursales")
    @ResponseBody Iterable<Sucursal> all() {
        return repository.findAll();
    }

    @PostMapping("/sucursales")
    @ResponseBody Sucursal newSucursal(@RequestBody Sucursal newSucursal) {
        //Validacion de sucursal
        if(repository.findByName(newSucursal.getName())!=null){
            log.error(String.format("La sucursal con el nombre %s ya existe", newSucursal.getName()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("La sucursal con el nombre %s ya existe", newSucursal.getName()));
        }
        Franquicia franquicia = franquiciaRepository.findByName(newSucursal.getFranquicia().getName());
        newSucursal.setFranquicia(franquicia);
        return repository.save(newSucursal);
    }


    @GetMapping("/sucursales/{id}")
    Sucursal getById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    

}
