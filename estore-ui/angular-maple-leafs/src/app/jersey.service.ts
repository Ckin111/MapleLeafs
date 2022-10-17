/**
 * JerseyService: service to connect to the JerseyAPI
 */

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
  
  /**
   * When you instantiate a jersey service
   * @param http to be able to talk to API
   */
  constructor(private http: HttpClient) { }

  /**
   * Will fetch all the jerseys from the API
   * @returns observable of the jerseys list
   */
  getJerseys(): Observable<Jersey[]> {
    console.log("Fetched all jerseys!")
    return this.http.get<Jersey[]>(this.jerseysUrl);
  }

  /**
   * Will get specified jersey based on id
   * @param id the id of the jersey to get
   * @returns Observable of a jersey
   */
  getJersey(id: number): Observable<Jersey> {
    const url = `${this.jerseysUrl}/${id}`;
    console.log("Fetched jersey ID: " + id);
    return this.http.get<Jersey>(url);
  }
  
}
