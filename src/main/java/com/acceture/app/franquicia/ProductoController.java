package com.acceture.app.franquicia;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acceture.app.entity.Franquicia;
import com.acceture.app.entity.Producto;
import com.acceture.app.entity.Sucursal;
import com.acceture.app.repository.FranquiciaRepository;
import com.acceture.app.repository.ProductoRepository;
import com.acceture.app.repository.SucursalRepository;

@RestController
public class ProductoController {

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);


    private final ProductoRepository repository;

    private final SucursalRepository sucursalRepository;

    private final FranquiciaRepository franquiciaRespository;

    ProductoController(ProductoRepository repository, SucursalRepository sucursalRepository,FranquiciaRepository franquiciaRespository) {
        this.repository = repository;
        this.sucursalRepository = sucursalRepository;
        this.franquiciaRespository = franquiciaRespository;
    }

    @GetMapping("/productos")
    @ResponseBody Iterable<Producto> all() {
        return repository.findAll();
    }

    @PostMapping("/productos")
    Producto newProducto(@RequestBody Producto newProducto) {
        if(repository.findByNameAndSucursal(newProducto.getName(),newProducto.getSucursal().getName())!=null){
            log.error(String.format("El producto %s ya existe para la sucursal %s ", newProducto.getName(), newProducto.getSucursal().getName()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,String.format("El producto %s ya existe para la sucursal %s ", newProducto.getName(), newProducto.getSucursal().getName()) );
        }
        Sucursal sucursal = sucursalRepository.findByName(newProducto.getSucursal().getName());
        newProducto.setSucursal(sucursal);
        return repository.save(newProducto);
    }


    @GetMapping("/productos/{id}")
    Producto getById(@PathVariable Long id) {
        return repository.findById(id).get();
    }


    @DeleteMapping("/productos")
    void deleteEmployee(@RequestBody Producto deleteProducto) {
        Producto producto = repository.findByNameAndSucursal(deleteProducto.getName(),deleteProducto.getSucursal().getName());
        if(producto==null){
            String msg = String.format("El producto %s no existe para la sucursal %s ", deleteProducto.getName(), deleteProducto.getSucursal().getName());
            log.error(msg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg );
        }
        repository.deleteById(producto.getId());
    }


    @PutMapping("/productos")
    Producto replaceProducto(@RequestBody Producto updateProducto) {
        Producto producto = repository.findByNameAndSucursal(updateProducto.getName(),updateProducto.getSucursal().getName());
        if(producto==null){
            String msg = String.format("El producto %s no existe para la sucursal %s ", updateProducto.getName(), updateProducto.getSucursal().getName());
            log.error(msg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg );
        }
        producto.setAmount(updateProducto.getAmount());
        return repository.save(producto);
    }

    
    @GetMapping("/productos/maxStock/{franquiciaName}")
    List<Producto> getById(@PathVariable String franquiciaName) {
        Franquicia franquicia = franquiciaRespository.findByNameWithSucursales(franquiciaName);
        List<Producto> maxProductoPorSucursal = new ArrayList<>();
        franquicia.getSucursales().forEach(sucursal-> {
            sucursalRepository.findByIdWithProductos(sucursal.getId());
            maxProductoPorSucursal.add(sucursal.getProductos().stream().max(Comparator.comparingInt(Producto::getAmount))
            .orElse(null));
        });
        return maxProductoPorSucursal;
    }

}
