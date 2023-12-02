import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  products : any;
  constructor(private httpClient : HttpClient) {
  }
  ngOnInit() {
    this.httpClient.get("http://localhost:8081/api/products")
      .subscribe({
        next : products => {
          this.products = products;
        },
        error : err => {
          console.log(err);
        }
      })
  }
}
