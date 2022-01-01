import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Role } from 'src/app/core/models/role';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  regForm: FormGroup;

  constructor(
    private authService: AuthService
  ) {
    this.regForm = new FormGroup({
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      fullName: new FormControl('', Validators.required),
      role: new FormControl(Role.Student, Validators.required)
    })
  }

  ngOnInit(): void {

  }

  get login() { return this.regForm.get('login'); }

  get password() { return this.regForm.get('password'); }

  get fullName() { return this.regForm.get('fullName'); }

  get role() { return this.regForm.get('role'); }

  register() {
    // if (this.regForm.valid)
    //   this.authService.register();
    console.log(this.regForm.value);
  }
}
