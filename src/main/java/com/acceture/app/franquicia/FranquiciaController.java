package com.acceture.app.franquicia;

import org.hibernate.NonUniqueResultException;
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
import com.acceture.app.repository.FranquiciaRepository;

@RestController
public class FranquiciaController {

    private static final Logger log = LoggerFactory.getLogger(FranquiciaController.class);


    private final FranquiciaRepository repository;

    FranquiciaController(FranquiciaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/franquicias")
    @ResponseBody Iterable<Franquicia> all() {
        return repository.findAll();
    }

    @PostMapping("/franquicias")
    Franquicia newFranquicia(@RequestBody Franquicia newFranquicia) {
        if(repository.findByName(newFranquicia.getName())!=null){
            log.error(String.format("La franquicia con el nombre %s ya existe", newFranquicia.getName()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,String.format("La franquicia con el nombre %s ya existe", newFranquicia.getName()));
        }
        return repository.save(newFranquicia);
    }


    @GetMapping("/franquicias/{id}")
    Franquicia getById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    /*@PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }*/

}
