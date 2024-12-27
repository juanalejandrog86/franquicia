package com.acceture.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.acceture.app.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto,Long>{

    @Query("SELECT p FROM Producto p cross join Sucursal s WHERE p.name = (:name) and s.name = (:sucursalName)")
    public Producto findByNameAndSucursal(@Param("name") String name,@Param("sucursalName") String sucursalName );

    @Query("SELECT p FROM Producto p cross join Sucursal s cross join Franquicia f WHERE f.name = (:franquiciaName) group by s.id having p.amount= max(p.amount)")
    public Iterable<Producto> findByFranquicia(@Param("franquiciaName") String franquiciaName );

}
