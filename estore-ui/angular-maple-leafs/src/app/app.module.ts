import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } 'from @angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
//import { ViewJerseyComponent } from './view-jersey/view-jersey.component';
import { LoginComponent } from './login/login.component';
import { AddJerseyComponent } from './add-jersey/add-jersey.component';

@NgModule({
  declarations: [
    AppComponent,
    //ViewJerseyComponent, //TODO: Fix above view-jersey import
    LoginComponent,
    AddJerseyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
