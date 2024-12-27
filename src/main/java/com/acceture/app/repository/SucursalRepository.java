package com.acceture.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.acceture.app.entity.Sucursal;

public interface SucursalRepository extends CrudRepository<Sucursal,Long>{

    @Query("SELECT s FROM Sucursal s left JOIN FETCH s.productos WHERE s.id = (:id)")
    public Sucursal findByIdWithProductos(@Param("id") Long id);

    @Query("SELECT s FROM Sucursal s WHERE s.name = (:name)")
    public Sucursal findByName(@Param("name") String name);


}
