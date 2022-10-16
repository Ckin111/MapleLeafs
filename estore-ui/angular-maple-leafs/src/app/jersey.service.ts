import { ComponentFactoryResolver, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jersey } from './jersey';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JerseyService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
  private jerseysUrl = 'http://localhost:8080/jerseys';
  
  constructor(private http: HttpClient) { }

  getJerseys(): Observable<Jersey[]> {
    console.log("Fetched all jerseys!")
    return this.http.get<Jersey[]>(this.jerseysUrl);
  }

  getJersey(id: number): Observable<Jersey> {
    const url = `${this.jerseysUrl}/${id}`;
    console.log("Fetched jersey ID: " + id);
    return this.http.get<Jersey>(url);
  }
  
}
