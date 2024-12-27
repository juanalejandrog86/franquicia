package com.acceture.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.acceture.app.entity.Franquicia;

public interface FranquiciaRepository extends CrudRepository<Franquicia,Long>{

    @Query("SELECT f FROM Franquicia f left JOIN FETCH f.sucursales WHERE f.id = (:id)")
    public Franquicia findByIdWithSucursales(@Param("id") Long id);

    @Query("SELECT f FROM Franquicia f WHERE f.name = (:name)")
    public Franquicia findByName(@Param("name") String name);

    
    @Query("SELECT f FROM Franquicia f left JOIN FETCH f.sucursales WHERE f.name = (:name)")
    public Franquicia findByNameWithSucursales(@Param("name") String name);

    @Query("SELECT f FROM Franquicia f left JOIN FETCH f.sucursales")
    public Iterable<Franquicia> findAllWithSucursales();


}
