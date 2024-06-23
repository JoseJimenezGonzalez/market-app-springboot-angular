import { Component, OnInit } from '@angular/core';
import { ConsultarPedidosService } from '../../service/ConsultarPedidos.service';
import { Pedido } from '../../model/Pedido';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-consultar-pedidos',
  templateUrl: './ConsultarPedidos.component.html',
  styleUrl: './ConsultarPedidos.component.css'
})
export class ConsultarPedidosComponent implements OnInit{

  usuario: string;
  pedidos: Pedido[];

  constructor(private consultarPedidosService: ConsultarPedidosService, private menuComponent: MenuComponent){}

  ngOnInit(): void {
    this.usuario = this.menuComponent.cliente.usuario;
    this.consultarPedidosService.consultarPedidos(this.usuario).subscribe(pedidos =>{
      this.pedidos = pedidos;
    });
  }

}
