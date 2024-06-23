package com.jose.microservicio_pedidos.services;

import java.util.List;
import java.util.Optional;

import com.jose.microservicio_pedidos.model.ElementosPedido;
import com.jose.microservicio_pedidos.model.Pedido;

public interface PedidosService {

    List<Pedido> pedidosUsuario(String usuario);
    Optional<Pedido> guardarPedido(List<ElementosPedido> elementosPedido, String usuario);
    
}
