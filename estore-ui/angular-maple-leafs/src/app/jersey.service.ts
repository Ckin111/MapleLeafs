import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JerseyService {

  private jerseysUrl = 'http://localhost:8080/jerseys';

  constructor() { }
}
