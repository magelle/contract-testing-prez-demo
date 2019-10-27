import { Component, OnInit } from '@angular/core';
import {Product} from "../typings";
import {CatalogService} from "../catalog.service";
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartIds: number[];
  cart: Product[];
  constructor(private catalogService: CatalogService) { }

  ngOnInit() {
    this.cartIds = [1,2,3];
    this.cartIds
      .forEach(id => this.getProduct(id));
  }

  private getProduct(id: number) {
    this.catalogService.getProduct(id)
      .subscribe(product => this.cart.push(product))
  }

}
