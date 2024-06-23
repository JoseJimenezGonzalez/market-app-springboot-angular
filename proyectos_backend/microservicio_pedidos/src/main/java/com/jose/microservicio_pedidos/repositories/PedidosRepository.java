package com.jose.microservicio_pedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.microservicio_pedidos.model.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Integer>{

    List<Pedido> findByUsuario(String usuario);

}
