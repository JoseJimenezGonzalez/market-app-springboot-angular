import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../model/Cliente';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
})
export class MenuComponent implements OnInit{

  enabled: boolean = false;
  cliente: Cliente;

  constructor(private router: Router){

  }

  ngOnInit(): void {
    this.router.navigate(["/portada"]);
  }

}
