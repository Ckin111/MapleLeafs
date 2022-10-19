import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewJerseyComponent } from './view-jersey/view-jersey.component';
import { LoginComponent } from './login/login.component';
import { BrowseJerseyComponent } from './browse-jersey/browse-jersey.component';
import { FormsModule } from '@angular/forms';
import { SearchJerseysComponent } from './search-jerseys/search-jerseys.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewJerseyComponent, 
    LoginComponent, BrowseJerseyComponent, SearchJerseysComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
