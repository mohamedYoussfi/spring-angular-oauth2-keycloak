import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './ui/products/products.component';
import { CustomersComponent } from './ui/customers/customers.component';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {HttpClientModule} from "@angular/common/http";
export function kcFactory(kcService : KeycloakService){
  return ()=>{
    kcService.init({
      config : {
        realm :"ebank-realm",
        clientId : "angular-client",
        url : "http://localhost:8080"
      },
      initOptions : {
        onLoad : "check-sso",
        checkLoginIframe : true,
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    })
  }
}

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    CustomersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    KeycloakAngularModule,
    HttpClientModule
  ],
  providers: [
    {provide : APP_INITIALIZER, deps : [KeycloakService],useFactory : kcFactory, multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
