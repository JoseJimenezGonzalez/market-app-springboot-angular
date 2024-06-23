package com.jose.microservicio_clientes.controllers;

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

import com.jose.microservicio_clientes.model.Cliente;
import com.jose.microservicio_clientes.services.ClientesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    ClientesService clientesService;

    @GetMapping(value = "/autenticar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> autenticar(@RequestParam("usuario") String usuario, @RequestParam("password") String password){
        Optional<Cliente> clienteAutenticadoOptional = clientesService.autenticarCliente(usuario, password);
    
        if(clienteAutenticadoOptional.isPresent()){
            return new ResponseEntity<>(clienteAutenticadoOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se ha autenticado", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrar(@RequestBody Cliente cliente){

        boolean registrado = clientesService.registrarCliente(cliente);
        if(registrado){
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

}
