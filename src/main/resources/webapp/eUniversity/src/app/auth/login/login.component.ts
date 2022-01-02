import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    this.authService.temp().subscribe();
    //let user: User = new User(1, 'aa', 'token', Role.Admin);
    //localStorage.setItem('user', JSON.stringify(user));

    //this.authService.login().subscribe(data => console.log(data));

    //this.authService.login().subscribe((data: String) => {console.log(data)});
  }

  /**
   * TODO: remove role
   */
  signIn(): void {
    if (this.loginForm.valid)
      this.authService.login(this.loginForm.value).subscribe((data: User) => {

        data.role = Role.Admin;

        if (data.role == Role.Admin) {
          console.log('Admin login');
          this.router.navigate(['/Admin']);
        }
        else if (data.role == Role.Teacher) {
          console.log('Teacher login');
          this.router.navigate(['/Student']);
        }
        else {
          console.log('Student login');
          this.router.navigate(['/Teacher']);
        }
      });
  }

}
