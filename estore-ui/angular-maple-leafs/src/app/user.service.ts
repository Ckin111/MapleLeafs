import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Jersey } from './jersey';
import { User } from './user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
  private usersUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.usersUrl, user, this.httpOptions).pipe(
      tap((newUser: User) => console.log(`added user w/username=${user.username}`))
      );
  }

  addToCart(name: string, jersey: Jersey): Observable<Jersey> {
    const url = `${this.usersUrl}/${name}/cart`;
    console.log("Added jersey: " + jersey.id + " to user: " + name);
    return this.http.post<Jersey>(this.usersUrl, jersey, this.httpOptions).pipe(
      tap((newJersey: Jersey) => console.log(`added jersey to user: ${name}`))
    );
  }

  removeFromCart(name: string, jersey: Jersey): Observable<Jersey> {
    const url = `${this.usersUrl}/${name}/cart`;
    //angular delete method only includes json body in the options param
    const deleteoptions = 
    {
      headers: new HttpHeaders({'Content-Type': 'application/json'}),
      body: jersey,
    };
    console.log("Removed jersey: " + jersey.id + " from user: " + name);
    return this.http.delete<Jersey>(url, deleteoptions);
  }

  getCart(name: string): Observable<Jersey[]> {
    const url = `${this.usersUrl}/${name}/cart`;
    console.log("Fetched cart from user: " + name);
    return this.http.get<Jersey[]>(url);
  }

}
