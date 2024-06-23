package com.jose.microservicio_pedidos.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.jose.microservicio_pedidos.model.ElementosPedido;
import com.jose.microservicio_pedidos.model.Pedido;
import com.jose.microservicio_pedidos.repositories.ElementosPedidosRepository;
import com.jose.microservicio_pedidos.repositories.PedidosRepository;

@Service
public class PedidosServiceImpl implements PedidosService{

    String urlProductos = "http://localhost:8001/productos/actualizarStock";

    @Autowired
    RestClient restClient;

    @Autowired
    ElementosPedidosRepository elementosPedidosRepository;

    @Autowired
    PedidosRepository pedidosRepository;

    @Override
    public List<Pedido> pedidosUsuario(String usuario) {
        return pedidosRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Pedido> guardarPedido(List<ElementosPedido> elementosPedido, String usuario) {
        try {
            //Creamos el objeto pedido y lo guardamos
            Pedido pedido = new Pedido(0, usuario, new Date(), "pendiente", null);
            Pedido p = pedidosRepository.save(pedido);
            //Guardamos los objetos elementos pedidos
            elementosPedido.forEach(e -> {
                e.setIdPedidoFk(p.getIdPedido());
                elementosPedidosRepository.save(e);
                //Actualizamos el stock
                UriComponentsBuilder builder =  UriComponentsBuilder
                .fromHttpUrl(urlProductos)
                .queryParam("idProducto", e.getProducto()
                .getIdProducto())
                .queryParam("unidades", e.getUnidades());
                restClient.put().uri(builder.toUriString()).retrieve();
            });
            return Optional.of(pedido);
        } catch (Exception e) {
            //Si hay excepcion se devuelve el optional vacio
            return Optional.empty();
        }
    }

}
