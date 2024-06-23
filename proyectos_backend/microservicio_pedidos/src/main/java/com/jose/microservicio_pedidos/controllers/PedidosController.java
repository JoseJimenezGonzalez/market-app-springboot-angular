package com.jose.microservicio_pedidos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jose.microservicio_pedidos.model.ElementosPedido;
import com.jose.microservicio_pedidos.model.Pedido;
import com.jose.microservicio_pedidos.services.PedidosService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidosController {

    @Autowired
    PedidosService pedidosService;

    @GetMapping(value = "/pedidosUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> pedidosUsuario(@RequestParam("usuario") String usuario){
        return new ResponseEntity<>(pedidosService.pedidosUsuario(usuario), HttpStatus.OK);
    }

    @PostMapping(value = "/guardarPedido", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> guardarPedido(@RequestBody List<ElementosPedido> elementosPedido, @RequestParam("usuario") String usuario){
        Optional<Pedido> pedidoOptional = pedidosService.guardarPedido(elementosPedido, usuario);
        if(!pedidoOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
