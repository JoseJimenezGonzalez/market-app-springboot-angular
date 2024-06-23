package com.jose.microservicio_productos.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.microservicio_productos.model.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Integer>{

    Set<Producto> findByIdCategoria(int idCategoria);
    
}
