import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private router: Router){}

  isuser: boolean = false;
  isadmin: boolean = false;
  loggedIn: boolean = false;
  adminUsername: String = "admin";
  adminPassword: String = "admin";
  userUsername: String = "user";
  userPassword: String = "user";
  message: String = "Please Enter Username and Password";



  checker (htmlUsername: String, htmlPassword: String ): boolean {
 
    if ( htmlUsername==this.adminUsername && htmlPassword==this.adminPassword){
      this.isadmin = true;
      this.loggedIn = true;
      this.message = "Logged In";
      this.admin()
      return true;
    } else if (htmlUsername==this.userUsername && htmlPassword==this.userPassword){
      this.isuser=true;
      this.loggedIn = true;
      this.message = "Logged In";
      this.user(this.userUsername)
      return true;
    } else {

      this.message = "Incorrect Username or Password"
      return false;
    }

  };

  user(username: String):void {
    // this function would redirect to browse and search
    this.router.navigate(["/user", {user: username}])
  }

  admin():void{
    // this function would redirect to browse, search, add, remove
    this.router.navigate(["/admin"])

  }

  ngOnInit(): void {
  }

}
