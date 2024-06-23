package com.jose.microservicio_clientes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.microservicio_clientes.model.Cliente;
import com.jose.microservicio_clientes.repositories.ClientesRepository;

@Service
public class ClientesServiceImpl implements ClientesService{

    @Autowired
    ClientesRepository clientesRepository;

    @Override
    public Optional<Cliente> autenticarCliente(String usuario, String password) {
        return clientesRepository.findByUsuarioAndPassword(usuario, password);
    }

    @Override
    public boolean registrarCliente(Cliente cliente) {
        
        Optional<Cliente> clienteOptional = clientesRepository.findById(cliente.getUsuario());
        if(clienteOptional.isPresent()){
            return false;
        }
        clientesRepository.save(cliente);
        return true;

    }

}
