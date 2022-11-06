import { Component, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router, RouterModule } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private router: Router, private userService: UserService){}

  tempUser:User = {
    id: 0,
    username: "user",
    cart: [],
    name: "user",
  }

  isuser: boolean = false;
  isadmin: boolean = false;
  loggedIn: boolean = false;
  adminUsername: String = "admin";
  userUsername: String = "test2";

  message: String = "Please Enter Username";

  checker (htmlUsername: String): boolean {
 
    if ( htmlUsername==this.adminUsername ){
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

  signup(htmlUsername: string): boolean{
    this.tempUser.username = htmlUsername;
    
    if ((this.userService.addUser(this.tempUser)) == null){
      this.message = "Username already exists";
      return false;
    } 
    this.message = "Signed Up!";
    return true;

    
  }

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
