import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from 'src/app/core/models/auth/auth';
import { Role } from 'src/app/core/models/auth/role';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = new FormGroup({
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    })
  }

  get login() { return this.loginForm.get('login'); }

  get password() { return this.loginForm.get('password'); }

  ngOnInit(): void {
    //let user: User = new User(1, 'aa', 'token', Role.Admin);
    //sessionStorage.setItem('user', JSON.stringify(user));

    //this.authService.temp().subscribe();
    //localStorage.setItem('user', JSON.stringify(user));

    //this.authService.login().subscribe(data => console.log(data));

    //this.authService.login().subscribe((data: String) => {console.log(data)});
  }

  signIn(): void {
    if (this.loginForm.valid)
      this.authService.login(this.loginForm.value).subscribe({
        next: (data: BaseResponse<Auth>) => {
          console.log(data);
          this.authService.saveUser(data.data);
          //let user: User = new User(data.data.id, data.data.fullName, data.data.jwtToken, data.data.role);

          //sessionStorage.setItem('user', JSON.stringify(user));
          //localStorage.setItem('rt', data.data.refreshToken);

          if (data.data.role == Role.Admin) {
            this.router.navigate(['/Admin']);
          }
          else if (data.data.role == Role.Teacher) {
            this.router.navigate(['/Teacher']);
          }
          else if (data.data.role == Role.Student) {
            this.router.navigate(['/Student']);
          }
          else {
            console.log('Announ login');
          }
        },
        error: (data) => {
          //alert(data);
        }
      });
  }

}
