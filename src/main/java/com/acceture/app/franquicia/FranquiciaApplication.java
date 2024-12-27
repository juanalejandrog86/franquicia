package com.acceture.app.franquicia;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.acceture.app.entity.Franquicia;
import com.acceture.app.entity.Producto;
import com.acceture.app.entity.Sucursal;
import com.acceture.app.repository.FranquiciaRepository;
import com.acceture.app.repository.ProductoRepository;
import com.acceture.app.repository.SucursalRepository;

@SpringBootApplication
@EntityScan("com.acceture.app.entity")
@EnableJpaRepositories("com.acceture.app.repository")
public class FranquiciaApplication {

	private static final Logger log = LoggerFactory.getLogger(FranquiciaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FranquiciaApplication.class, args);
	}

	/* 
	@Autowired
  public void testFranquicia(FranquiciaRepository repository, SucursalRepository sucursalRepository, ProductoRepository productoRepository) {
	 log.info("Creating data");
	 Franquicia f1 = new Franquicia("F1");

	 
	 repository.save(new Franquicia( "F2"));
	 repository.save(new Franquicia( "F3"));
	 
	 
	 List<Producto> productos = new ArrayList<>();
	 Sucursal s1 = new Sucursal("S1");
	 List<Sucursal> sucursales = new ArrayList<>();
	 Producto p1=new Producto("P1",100);
	 productos.add(p1);
	 p1.setSucursal(s1);
	 s1.setProductos(productos);
	 s1.setFranquicia(f1);
	 sucursales.add(s1);
	 f1.setSucursales(sucursales);

	 repository.save(f1);

	 repository.findAll().forEach(franquicia -> {
		log.info(franquicia.toString());
		repository.findByIdWithSucursales(franquicia.getId()).getSucursales()
		.forEach(sucursal -> {
			log.info(sucursal.toString()+ "-> Franquicia "+ franquicia.getName());
			sucursalRepository.findByIdWithProductos(sucursal.getId()).getProductos()
			.forEach(producto-> log.info(producto.toString()+"-> Sucursal "+sucursal.getName()));
		});
	});

  }*/
}
