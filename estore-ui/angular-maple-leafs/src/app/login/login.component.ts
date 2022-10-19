import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  isuser: boolean = false;
  isadmin: boolean = false;
  loggedIn: boolean = false;
  adminUsername: String = "admin";
  adminPassword: String = "admin";
  message: String = "Please Enter Username and Password";

  checker (htmlUsername: String, htmlPassword: String ): boolean {
 
    if ( htmlUsername==this.adminUsername && htmlPassword==this.adminPassword){
      this.isadmin = true;
      this.loggedIn = true;
      this.message = "Logged In";
      return true;
    }else {

      this.message = "Incorrect Username or Password"
      return false;
    }

  };

  ngOnInit(): void {
  }

}
