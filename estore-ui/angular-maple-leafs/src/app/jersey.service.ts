import { Injectable } from '@angular/core';
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

  getJersey(id: number): Observable<Jersey> {
    const url = `${this.jerseysUrl}/${id}`;
    console.log("I work");
    return this.http.get<Jersey>(url);
  }
  
}
