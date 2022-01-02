import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegDTO } from '../core/DTOs/reg-dto';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private readonly controllerName: string = '/admin-panel/accounts';

  constructor(
    private http: HttpClient
  ) { }

  registerAdmin(dto: RegDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'register-admin');
    return this.http.post(url, dto);
  }

  getAdmins() {
    let url: string = PrepareApi.prepare(this.controllerName, 'admins');
    return this.http.get(url);
  }

  updateAdmin(dto: RegDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'update-admin');
    return this.http.put(url, dto);
  }

  registerTeacher(dto: RegDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'register-teacher');
    return this.http.post(url, dto);
  }

  getTeachers() {
    let url: string = PrepareApi.prepare(this.controllerName, 'teachers');
    return this.http.get(url);
  }

  updateTeachers(dto: RegDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'update-teacher');
    return this.http.put(url, dto);
  }

  registerStudent(dto: RegDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'register-student');
    return this.http.post(url, dto);
  }

  getStudents() {
    let url: string = PrepareApi.prepare(this.controllerName, 'students');
    return this.http.get(url);
  }

  updateStudents(dto: RegDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'update-student');
    return this.http.put(url, dto);
  }

}
