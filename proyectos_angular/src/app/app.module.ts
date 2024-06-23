import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './controller/login/login.component';
import { RegistrarComponent } from './controller/registrar/registrar.component';
import { ProcesarPedidoComponent } from './controller/procesar-pedido/procesar-pedido.component';
import { ConsultarPedidosComponent } from './controller/ConsultarPedidos/ConsultarPedidos.component';
import { MenuComponent } from './controller/menu/menu.component';

@NgModule({
  declarations: [
    MenuComponent,
    LoginComponent,
    RegistrarComponent,
    ProcesarPedidoComponent,
    ConsultarPedidosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    //Este modulo es muy importante
    HttpClientModule,
    //Directiva para el formulario
    FormsModule
  ],
  providers: [],
  //Aqui le decimos donde empieza
  bootstrap: [MenuComponent]
})
export class AppModule { }
