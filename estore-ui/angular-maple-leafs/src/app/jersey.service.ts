import { Injectable } from '@angular/core';
import { Jersey } from './jersey';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class JerseyService {

  private jerseysUrl = 'http://localhost:8080/jerseys';
  
  constructor(private http: HttpClient) { }

  addJersey(jersey: Jersey): Observable<Jersey> {
      return this.http.post<Jersey>(this.jerseysUrl, jersey, this.httpOptions).pipe(
        tap((newJersey: Jersey) => this.log(`added jersey w/id=${newJersey.id}`)),
        catchError(this.handleError<Jersey>('addJersey'))
      );
  }

}
