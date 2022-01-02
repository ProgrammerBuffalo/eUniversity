import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Role } from 'src/app/core/models/role';
import { User } from 'src/app/core/models/user';
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
    this.authService.temp().subscribe();
    //let user: User = new User(1, 'aa', 'token', Role.Admin);
    //localStorage.setItem('user', JSON.stringify(user));

    //this.authService.login().subscribe(data => console.log(data));

    //this.authService.login().subscribe((data: String) => {console.log(data)});
  }

  signIn(): void {
    if (this.loginForm.valid)
      this.authService.login(this.loginForm.value).subscribe(data => {
        localStorage.setItem('user', data.user);
      });
  }

}
