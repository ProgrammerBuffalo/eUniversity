import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddAdminDTO } from '../core/DTOs/admin/add-admin-dto';
import { AddStudentDTO } from '../core/DTOs/admin/add-student-dto';
import { AddTeacherDTO } from '../core/DTOs/admin/add-teacher-dto';
import { UpdateStudentDTO } from '../core/DTOs/admin/update-student-dto';
import { RegDTO } from '../core/DTOs/reg-dto';
import { Student } from '../core/models/admin/student';
import { BaseResponse } from '../core/models/base/base-response';
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

  deleteTeacher(id: string) {
    let url: string = PrepareApi.prepare(this.controllerName, 'update-teacher');
    let params = { id: id };
    return this.http.delete(url, { params: params });
  }

  registerStudent(dto: AddStudentDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'register-student');
    return this.http.post(url, dto);
  }

  getStudents(): Observable<BaseResponse<Student[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'students');
    return this.http.get<BaseResponse<Student[]>>(url);
  }

  updateStudent(dto: UpdateStudentDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'update-student');
    return this.http.put(url, dto);
  }

  deleteStudent(id: string) {
    let url: string = PrepareApi.prepare(this.controllerName, 'delete-student');
    let params = { id: id };
    return this.http.delete(url, { params: params });
  }
}
