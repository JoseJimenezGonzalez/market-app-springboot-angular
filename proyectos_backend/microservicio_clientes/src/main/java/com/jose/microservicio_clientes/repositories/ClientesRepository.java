package com.jose.microservicio_clientes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.microservicio_clientes.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, String>{

    Optional<Cliente> findByUsuarioAndPassword(String usuario, String password);
    
}
