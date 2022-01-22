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
  }

  signIn(): void {
    this.router.navigate(['/Admin']);

    // if (this.loginForm.valid)
    //   this.authService.login(this.loginForm.value).subscribe({
    //     next: (data: BaseResponse<Auth>) => {
    //       this.authService.saveUser(data.data);

    //       if (data.data.role == Role.Admin) {
    //         this.router.navigate(['/Admin']);
    //       }
    //       else if (data.data.role == Role.Teacher) {
    //         this.router.navigate(['/Teacher']);
    //       }
    //       else if (data.data.role == Role.Student) {
    //         this.router.navigate(['/Student']);
    //       }
    //       else {
    //         console.log('Announ login');
    //       }
    //     },
    //     error: (data) => {
    //       alert('wrong login or password');
    //     }
    //   });
  }

}
