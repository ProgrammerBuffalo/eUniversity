import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../core/models/admin/student';
import { BaseResponse } from '../core/models/base/base-response';
import { DDL } from '../core/models/ddl';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private readonly controllerName: string = '/admin-panel/groups';

  constructor(
    private http: HttpClient
  ) { }

  getAllGroupsDDL(): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-students-group');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }

  getStudentsByGroup(id: string): Observable<BaseResponse<Student[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-students-by-group');
    let param = { groupId: id }
    return this.http.get<BaseResponse<Student[]>>(url, { params: param });
  }
}
