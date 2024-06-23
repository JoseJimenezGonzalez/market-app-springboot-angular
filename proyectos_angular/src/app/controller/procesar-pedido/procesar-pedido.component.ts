import { Component, OnInit } from '@angular/core';
import { Categoria } from '../../model/Categoria';
import { Producto } from '../../model/Producto';
import { ProcesarPedidoService } from '../../service/procesar-pedido.service';
import { CestaItem } from '../../model/CestaItem';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-procesar-pedido',
  templateUrl: './procesar-pedido.component.html',
  styleUrl: './procesar-pedido.component.css'
})
export class ProcesarPedidoComponent implements OnInit{ 

  categorias: Categoria[];
  productos: Producto[];
  idCategoriaSeleccionada: number;
  cesta: CestaItem[];

  constructor(private procesarPedidoService: ProcesarPedidoService, private menuComponent: MenuComponent){

  }

  ngOnInit(): void {

    this.cesta = [];

    this.procesarPedidoService.categorias().subscribe(categorias => {
      this.categorias = categorias;
    })
  }

  productosPorCategoria(){
    this.procesarPedidoService.productosPorCategorias(this.idCategoriaSeleccionada).subscribe(productos => {
      this.productos = productos;
      this.actualizarStocks();
    })
  }
  
  agregarProductoCesta(producto: Producto){
    if(producto.unidades <= producto.stock){
      let item = new CestaItem();
      item.producto = producto;
      item.unidades = producto.unidades;
      //actualizar stock
      producto.stock = producto.stock - producto.unidades;

      this.cesta.push(item);
    }else{
      alert("No hay suficiente stock");
    }
  }

  eliminarProductoCesta(posicion: number){
    let item = this.cesta[posicion];
    this.cesta.splice(posicion, 1);
    let producto = this.productos.find(producto => producto.idProducto === item.producto.idProducto);
    if (producto) {
      producto.stock = Number(producto.stock) + Number(item.unidades);
    }
  }

  actualizarStocks(){
    this.productos.forEach(producto => {
      this.cesta.forEach(productoCesta => {
        if(producto.idProducto === productoCesta.producto.idProducto){
          producto.stock = producto.stock - productoCesta.unidades;
        }
      });
    });
  }

  procesarPedido(){
    this.procesarPedidoService.enviarPedido(this.cesta,this.menuComponent.cliente.usuario).subscribe({
      next: response => alert("Pedido procesado"),
      error: error => alert("El pedido no se ha procesado")
    });
  }


}
