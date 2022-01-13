import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddGroupDTO } from '../core/DTOs/admin/add-group';
import { UpdateGroupDTO } from '../core/DTOs/admin/updae-group';
import { Group } from '../core/models/admin/group';
import { Student } from '../core/models/admin/student';
import { BaseResponse } from '../core/models/base/base-response';
import { DDL } from '../core/models/ddl';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private readonly controllerName: string = 'admin-panel/groups';

  constructor(
    private http: HttpClient
  ) { }

  addGroup(dto: AddGroupDTO): Observable<BaseResponse<number>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'add-group');
    return this.http.post<BaseResponse<number>>(url, dto);
  }

  getAllGroups(): Observable<BaseResponse<Group[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-groups');
    return this.http.get<BaseResponse<Group[]>>(url);
  }

  updateGroup(dto: UpdateGroupDTO): Observable<BaseResponse<string>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'edit-group-info');
    return this.http.put<BaseResponse<string>>(url, dto);
  }

  removeGroup(id: number): Observable<BaseResponse<string>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'delete-group');
    let params = { groupId: id };
    return this.http.delete<BaseResponse<string>>(url, { params: params });
  }

  getAllGroupsDDL(): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-all-groups-ddl');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }

  getAllStudentsWithoutGroup(): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-students-without-group');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }

  //
  getStudentsByGroup(id: number): Observable<BaseResponse<Student[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-students-by-group');
    let param = { groupId: id }
    return this.http.get<BaseResponse<Student[]>>(url, { params: param });
  }

  //
  attachStudent(groupId: number, studentId: number): Observable<BaseResponse<any>> {
    let url: string = PrepareApi.prepare(this.controllerName, '');
    let params = { groupId: groupId, studentId: studentId };
    return this.http.post<BaseResponse<any>>(url, params);
  }

  //
  detachStudent(groupId: number, studentId: number) {
    let url: string = PrepareApi.prepare(this.controllerName, '');
    let params = { groupId: groupId, studentId: studentId };
    return this.http.delete<BaseResponse<any>>(url, { params: params });
  }
}
