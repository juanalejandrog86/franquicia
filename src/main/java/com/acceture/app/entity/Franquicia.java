package com.acceture.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Franquicia {
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  @OneToMany(mappedBy = "franquicia", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Sucursal> sucursales;

    public Franquicia(){}

    public Franquicia(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Franquicia [id=" + id + ", name=" + name + "]";
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

}
