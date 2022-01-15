import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResponse } from '../core/models/base/base-response';
import { TeacherDiscipline } from '../core/models/admin/teacher-discipline';
import { TeacherShortDisciplines } from '../core/models/admin/teacher-short-disciplines';
import { AttachDisciplineDTO } from '../core/DTOs/admin/attach-discipline-dto';
import { PrepareApi } from './prepare-api';
import { DDL } from '../core/models/ddl';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  private controllerName: string = 'admin-panel/discipline'

  constructor(
    private http: HttpClient
  ) { }

  attachDiscipline(dto: AttachDisciplineDTO): Observable<BaseResponse<any>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'attach-discipline');
    return this.http.post<BaseResponse<any>>(url, dto);
  }

  getTeachersShortDisciplines(): Observable<BaseResponse<TeacherShortDisciplines[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-teachers-short-disciplines');
    return this.http.get<BaseResponse<TeacherShortDisciplines[]>>(url);
  }

  getTeacherDisciplines(teacherId: number): Observable<BaseResponse<TeacherDiscipline[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-teacher-disciplines')
    let params = { teacherId: teacherId };
    return this.http.get<BaseResponse<TeacherDiscipline[]>>(url, { params: params });
  }

  getTeacherDisciplinesDDL(teacherId: number): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-teacher-disciplines-ddl');
    let params = { teacherId: teacherId };
    return this.http.get<BaseResponse<DDL<number>[]>>(url, { params: params });
  }

  getTeacherShortDisciplines(teacherId: number): Observable<BaseResponse<TeacherShortDisciplines>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-teacher-short-disciplines');
    let params = { teacherId: teacherId };
    return this.http.get<BaseResponse<TeacherShortDisciplines>>(url, { params: params });
  }

  detachDiscipline(teacherId: number, disciplineId: number): Observable<BaseResponse<any>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'detach-discipline');
    let params = { teacherId: teacherId, disciplineId: disciplineId };
    return this.http.delete<BaseResponse<any>>(url, { params: params });
  }
}
