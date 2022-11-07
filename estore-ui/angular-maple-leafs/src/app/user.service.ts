import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { User } from './user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
  private usersUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<User> {
    // LOG.info("POST /users/?name=" + name)
    return this.http.post<User>(this.usersUrl, user, this.httpOptions).pipe(
      tap((newUser: User) => console.log(`added user w/username=${user.username}`))
      );
  }

  getUser(user: User): Observable<User> {
    // LOG.info("GET /users/" + name);
    const url = `${this.usersUrl}/${user.username}`;
    console.log("Fetched username: " + user.username);
    return this.http.get<User>(url);
  }

}
