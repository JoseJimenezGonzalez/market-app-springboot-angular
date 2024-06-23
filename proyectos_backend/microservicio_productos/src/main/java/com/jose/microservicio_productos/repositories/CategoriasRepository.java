package com.jose.microservicio_productos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.microservicio_productos.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer>{

}
