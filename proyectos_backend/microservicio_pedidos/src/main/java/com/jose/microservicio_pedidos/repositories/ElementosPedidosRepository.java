package com.jose.microservicio_pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.microservicio_pedidos.model.ElementosPedido;

public interface ElementosPedidosRepository extends JpaRepository<ElementosPedido, Integer> {

}
