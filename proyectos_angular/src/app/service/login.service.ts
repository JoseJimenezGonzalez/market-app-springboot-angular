import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  login(usuario: string, password: string): Observable<any>{

    let url = "http://localhost:8080/clientes/autenticar";
    let params = new HttpParams();
    params = params.append("usuario", usuario);
    params = params.append("password", password);
    //Nos devuelve un observable
    return this.http.get(url, {params: params});

  }

}
