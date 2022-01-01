import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
    private authService: AuthService
  ) {
    this.loginForm = new FormGroup({
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    })
  }

  get login() { return this.loginForm.get('login'); }

  get password() { return this.loginForm.get('password'); }

  ngOnInit(): void {
    //this.authService.login().subscribe(data => console.log(data));

    //this.authService.login().subscribe((data: String) => {console.log(data)});
  }

  signIn(): void {
    // if (this.loginForm.valid)
    //   this.authService.login();
    console.log(this.loginForm.value);
  }

}
