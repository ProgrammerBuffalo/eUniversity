import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddAdminDTO } from '../core/DTOs/admin/add-admin-dto';
import { AddStudentDTO } from '../core/DTOs/admin/add-student-dto';
import { AddTeacherDTO } from '../core/DTOs/admin/add-teacher-dto';
import { RegDTO } from '../core/DTOs/reg-dto';
import { Student } from '../core/models/admin/student';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private readonly controllerName: string = 'admin-panel/accounts';

  constructor(
    private http: HttpClient
  ) { }

  registerAdmin(dto: AddAdminDTO) {
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

  deleteAdmin(id: number) {
    let url: string = PrepareApi.prepare(this.controllerName, '');
    let params = { id: id };
    return this.http.delete(url, { params: params });
  }

  registerTeacher(dto: AddTeacherDTO) {
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

  registerStudent(dto: AddStudentDTO): Observable<number> {
    let url: string = PrepareApi.prepare(this.controllerName, 'register-student');
    return this.http.post<number>(url, dto);
  }

  getStudents(): Observable<Student[]> {
    let url: string = PrepareApi.prepare(this.controllerName, 'students');
    return this.http.get<Student[]>(url);
  }

  updateStudents(dto: RegDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'update-student');
    return this.http.put(url, dto);
  }

}
