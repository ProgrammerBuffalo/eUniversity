import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../_services/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(
    private authService: AuthenticationService
  ) { }
  username: string = "student1";
  password: string = "student1";


  ngOnInit(): void {
  }

  login() {
    var data = this.authService.login(this.username, this.password);
    console.log(data);
  }
}
