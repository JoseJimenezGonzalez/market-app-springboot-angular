package com.jose.microservicio_productos.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.microservicio_productos.model.Categoria;
import com.jose.microservicio_productos.model.Producto;
import com.jose.microservicio_productos.repositories.CategoriasRepository;
import com.jose.microservicio_productos.repositories.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService{

    @Autowired
    CategoriasRepository categoriasRepository;

    @Autowired
    ProductosRepository productosRepository;

    @Override
    public Set<Categoria> categorias() {
        List<Categoria> listaCategorias = categoriasRepository.findAll(); // Asumiendo que esto devuelve una List
        return new HashSet<>(listaCategorias); // Convertir a Set y retornar
    }

    @Override
    public Set<Producto> productoPorCategoria(int idCategoria) {
        return productosRepository.findByIdCategoria(idCategoria);
    }

    @Override
    public Optional<Producto> actualizarStock(int idProducto, int unidades) {
        Optional<Producto> optionalProducto = productoPorCodigo(idProducto);
        if(optionalProducto.isPresent()){
            Producto producto = optionalProducto.get();
            if(producto.getStock() >= unidades){
                producto.setStock(producto.getStock() - unidades);
                productosRepository.save(producto);
                return Optional.of(producto);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Producto> productoPorCodigo(int idProducto) {
        return productosRepository.findById(idProducto);
    }

}
