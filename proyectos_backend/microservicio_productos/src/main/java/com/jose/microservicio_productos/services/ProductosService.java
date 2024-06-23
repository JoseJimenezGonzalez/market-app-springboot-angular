package com.jose.microservicio_productos.services;

import java.util.Optional;
import java.util.Set;

import com.jose.microservicio_productos.model.Categoria;
import com.jose.microservicio_productos.model.Producto;

public interface ProductosService {

    Set<Categoria> categorias();
    Set<Producto> productoPorCategoria(int idCategoria);
    Optional<Producto> actualizarStock(int idProducto, int unidades);
    Optional<Producto> productoPorCodigo(int idProducto);

}
