import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  loggedIn: boolean = false;
  adminUsername: String = "admin";
  adminPassword: String = "admin";
  message: String = "Please Enter Username and Password";
  enteredUsername: String= "";
  enteredPassword: String= "";

  
  checker (htmlUsername: String, htmlPassword: String ): boolean {
    // let username = document.getElementById('username')
    // const username_value: String = username.value;
    
    if (htmlUsername==this.adminUsername && htmlPassword==this.adminPassword){
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
