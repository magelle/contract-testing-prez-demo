import {Injectable} from '@angular/core';
import {Product} from "./typings";
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs/internal/Observable";

@Injectable({
  providedIn: 'root'
})
export class CatalogService {
  private catalogUrl  = '/products';

  constructor(private http: HttpClient) {
  }

  getProduct(id: number): Observable<Product>{
    return this.http.get<Product>(this.catalogUrl + '/'+ id)
  }

}
