import { Component, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router, RouterModule } from '@angular/router';

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
  userUsername: String = "user";
  message: String = "Please Enter Username and Password";

  checker (htmlUsername: String): boolean {
 
    if ( htmlUsername==this.adminUsername){
      this.isadmin = true;
      this.loggedIn = true;
      this.message = "Logged In";
      this.user(htmlUsername);
      return true;
    } else if (htmlUsername==this.userUsername){
      this.isuser=true;
      this.loggedIn = true;
      this.message = "Logged In";
      this.user(this.userUsername)
      return true;
    } else {

      this.message = "Incorrect Username"
      return false;
    }

  };

  user(username: String):void {
    // this function would redirect to browse and search
    if(username == "admin"){
      this.router.navigateByUrl("/admin");
    }else{
      this.router.navigate(["user", {user: username}])
    }
  }


  ngOnInit(): void {
  }

}
