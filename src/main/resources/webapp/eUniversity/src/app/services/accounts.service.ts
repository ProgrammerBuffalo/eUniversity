import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddAdminDTO } from '../core/DTOs/admin/account/add-admin-dto';
import { AddStudentDTO } from '../core/DTOs/admin/account/add-student-dto';
import { AddTeacherDTO } from '../core/DTOs/admin/account/add-teacher-dto';
import { UpdateAdminDTO } from '../core/DTOs/admin/account/update-admin-dto';
import { UpdateStudentDTO } from '../core/DTOs/admin/account/update-student-dto';
import { UpdateTeacherDTO } from '../core/DTOs/admin/account/update-teacher-dto';
import { PaginationDTO } from '../core/DTOs/pagination-dto';
import { Admin } from '../core/models/admin/account/admin';
import { Student } from '../core/models/admin/account/student';
import { Teacher } from '../core/models/admin/account/teacher';
import { BaseResponse } from '../core/models/base/base-response';
import { PaginatedList } from '../core/models/paginated-list';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private readonly controllerName: string = 'admin-panel/accounts';

  constructor(
    private http: HttpClient
  ) { }

  registerAdmin(dto: AddAdminDTO): Observable<BaseResponse<Admin>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'register-admin');
    return this.http.post<BaseResponse<Admin>>(url, dto);
  }

  getAdmins(dto: Readonly<PaginationDTO>): Observable<BaseResponse<PaginatedList<Admin>>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'admins');
    return this.http.get<BaseResponse<PaginatedList<Admin>>>(url, { params: dto });
  }

  updateAdmin(dto: UpdateAdminDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'update-admin');
    return this.http.put(url, dto);
  }

  deleteAdmin(id: string) {
    let url: string = PrepareApi.prepare(this.controllerName, 'delete-admin');
    let params = { id: id };
    return this.http.delete(url, { params: params });
  }

  registerTeacher(dto: AddTeacherDTO): Observable<BaseResponse<Teacher>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'register-teacher');
    return this.http.post<BaseResponse<Teacher>>(url, dto);
  }

  getTeachers(dto: Readonly<PaginationDTO>): Observable<BaseResponse<PaginatedList<Teacher>>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'teachers');
    return this.http.get<BaseResponse<PaginatedList<Teacher>>>(url, { params: dto });
  }

  updateTeacher(dto: UpdateTeacherDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'update-teacher');
    return this.http.put(url, dto);
  }

  deleteTeacher(id: string) {
    let url: string = PrepareApi.prepare(this.controllerName, 'delete-teacher');
    let params = { id: id };
    return this.http.delete(url, { params: params });
  }

  registerStudent(dto: AddStudentDTO): Observable<BaseResponse<Student>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'register-student');
    return this.http.post<BaseResponse<Student>>(url, dto);
  }

  getStudents(dto: Readonly<PaginationDTO>): Observable<BaseResponse<PaginatedList<Student>>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'students');
    return this.http.get<BaseResponse<PaginatedList<Student>>>(url, { params: dto });
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
