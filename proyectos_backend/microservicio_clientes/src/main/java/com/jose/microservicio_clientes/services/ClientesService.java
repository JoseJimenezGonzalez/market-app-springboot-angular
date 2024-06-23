package com.jose.microservicio_clientes.services;

import java.util.Optional;

import com.jose.microservicio_clientes.model.Cliente;

public interface ClientesService {

    Optional<Cliente> autenticarCliente(String usuario, String password);

    boolean registrarCliente(Cliente cliente);

}
