import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AttachScheduleDTO } from '../core/DTOs/admin/add-schedule.dto';
import { Schedule } from '../core/models/admin/schedule';
import { BaseResponse } from '../core/models/base/base-response';
import { DDL } from '../core/models/ddl';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private controllerName: string = 'admin-panel/schedule';

  constructor(
    private http: HttpClient
  ) { }

  //
  getScheduleLessons(groupId: number): Observable<BaseResponse<Schedule[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-group-schedule-lessons');
    let params = { id: groupId };
    return this.http.get<BaseResponse<Schedule[]>>(url, { params: params });
  }

  getScheduleExams(groupId: number) {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-group-schedule-exams');
    let params = { id: groupId };
    return this.http.get<BaseResponse<Schedule[]>>(url, { params: params });
  }

  attachSchedule(dto: AttachScheduleDTO): Observable<BaseResponse<Schedule>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'attach-schedule-row');
    return this.http.post<BaseResponse<Schedule>>(url, dto);
  }

  detachSchedule(id: number) {
    let url: string = PrepareApi.prepare(this.controllerName, 'detach-schedule-row');
    let params = { scheduleId: id };
    return this.http.delete(url, { params: params });
  }

  getLessonsDDL(): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-lessons-ddl');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }

  getExamsDDL(): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-exams-ddl');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }

  getEduProccessesDDL(): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-education-processes-ddl');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }
  
}
