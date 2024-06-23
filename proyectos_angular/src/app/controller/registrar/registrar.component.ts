import { Component } from '@angular/core';
import { RegistrarService } from '../../service/registrar.service';
import { Cliente } from '../../model/Cliente';
import { catchError, of } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css',
})
export class RegistrarComponent { 

  cliente: Cliente = new Cliente();
  constructor(private registrarService: RegistrarService, private router: Router){}

  registrar(){
    this.registrarService.registrar(this.cliente).pipe(
      catchError( error => {
        if(error.status === 409){
          alert("Ya hay un usuario con ese username en la base de datos, pruebe con otro");
        }else{
          alert("Ocurrió un error durante el registro");
        }
        return of (null);
      })
    ).subscribe(data => {
      if (data) {
        this.cliente = data;
        alert("Usuario registrado");
        this.router.navigate(["/login"]);
      }
    });
  }
}
