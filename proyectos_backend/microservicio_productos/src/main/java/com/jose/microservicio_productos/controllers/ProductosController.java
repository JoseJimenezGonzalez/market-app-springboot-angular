package com.jose.microservicio_productos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jose.microservicio_productos.model.Producto;
import com.jose.microservicio_productos.services.ProductosService;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductosController {

    @Autowired
    ProductosService productosService;

    @GetMapping(value = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> categorias(){
        return new ResponseEntity<>(productosService.categorias(), HttpStatus.OK);
    }

    @GetMapping(value = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> productosCategorias(@RequestParam("idCategoria") int idCategoria){
        return new ResponseEntity<>(productosService.productoPorCategoria(idCategoria), HttpStatus.OK);
    }

    @GetMapping(value = "/producto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> productosCodigo(@RequestParam("idProducto") int idProducto){

        Optional<Producto> productoOptional = productosService.productoPorCodigo(idProducto);
        if(productoOptional.isPresent()){
            return new ResponseEntity<>(productoOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Ese producto no está en nuestra base de datos", HttpStatus.NOT_FOUND);
        }
    
    }

    @PutMapping(value = "/actualizarStock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarStock(@RequestParam("idProducto") int idProducto, @RequestParam("unidades") int unidades){

        Optional<Producto> productoOptional = productosService.actualizarStock(idProducto, unidades);
        if(productoOptional.isPresent()){
            return new ResponseEntity<>(productoOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("El stock no se ha podido actualizar ya que las unidades introducidas no son correctas", HttpStatus.CONFLICT);
        }
    
    }




}
