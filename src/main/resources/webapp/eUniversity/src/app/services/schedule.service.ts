import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddScheduleDTO } from '../core/DTOs/admin/add-schedule.dto';
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
  getGroupSchedule(groupId: number): Observable<BaseResponse<Schedule[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-group-schedule');
    let params = { id: groupId };
    return this.http.get<BaseResponse<Schedule[]>>(url, { params: params });
  }

  //Observable<BaseResponse<DDL<number>[]>>
  tempGetGroupDisciplinesDDL(groupId: number) {
    let url: string = PrepareApi.prepare(this.controllerName, '');
    let params = { groupId: groupId };
    //return this.http.get<BaseResponse<DDL<number>[]>>(url, { params: params });

    let ddl: DDL<number>[] = [{ id: 1, name: 'dis1' }, { id: 1, name: 'dis2' }, { id: 1, name: 'dis3' }];
    return ddl;
  }

  //
  tempGetTeachersDDL() {
    let temp: DDL<number>[] = [{ id: 11, name: 'aa' }, { id: 22, name: 'bb' }, { id: 33, name: 'cc' }]
    return temp;
  }

  //
  tempAddSchedule(dto: AddScheduleDTO) {
    // let url: string = PrepareApi.prepare(this.controllerName, '');
    // this.http.post(url, dto);
  }

  //
  // getSchedules(groupId: number) {
  //   let scheduleDiscipline: ScheduleDiscipline[] = [
  //     { teacherName: 'teacher1', weekNum: 1, to: new Date(), from: new Date() },
  //     { teacherName: 'teacher2', weekNum: 2, to: new Date(), from: new Date() },
  //     { teacherName: 'teacher3', weekNum: 3, to: new Date(), from: new Date() }];

  //   let schedule: Schedule[] = [
  //     { disciplineId: 11, disciplineName: 'dis1', scheduleDiscipline },
  //     { disciplineId: 12, disciplineName: 'dis2', scheduleDiscipline }];

  //   return schedule;
  // }
}
