import { Component } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { Cliente } from '../../model/Cliente';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent { 

  usuario: string;
  password: string;
  cliente: Cliente;

  constructor(private loginService: LoginService, private menuComponent: MenuComponent){}

  login(){
    this.loginService.login(this.usuario, this.password).pipe(
      catchError(error => {
        // Si el login falla, el backend debería devolver un error.
        // Aquí puedes manejar ese error.
        if (error.status === 401) {
          alert("Usuario no autenticado");
        } else {
          alert("Ocurrió un error durante el login");
        }
        return of(null); // Devuelve un observable para mantener la cadena.
      })
    ).subscribe(data => {
      // Si el login es exitoso, el backend debería devolver los datos del cliente.
      if (data) {
        this.cliente = data;
        this.menuComponent.cliente = this.cliente;
        this.menuComponent.enabled = true;
        alert("Usuario autenticado");
      }
    });
}

}
