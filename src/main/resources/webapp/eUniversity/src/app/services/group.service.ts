import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddGroupDTO } from '../core/DTOs/admin/group/add-group';
import { AttachStudentDTO } from '../core/DTOs/admin/group/attach-student-dto';
import { GroupDisciplineDTO } from '../core/DTOs/admin/group/group-discipline-dto';
import { UpdateGroupDTO } from '../core/DTOs/admin/group/updae-group';
import { Group } from '../core/models/admin/group';
import { GroupDiscipline } from '../core/models/admin/group-discipline';
import { Student } from '../core/models/admin/account/student';
import { BaseResponse } from '../core/models/base/base-response';
import { DDL } from '../core/models/ddl';
import { PrepareApi } from './prepare-api';
import { PaginatedList } from '../core/models/paginated-list';
import { PaginationDTO } from '../core/DTOs/pagination-dto';

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

  getAllGroups(dto: Readonly<PaginationDTO>): Observable<BaseResponse<PaginatedList<Group>>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-groups');
    return this.http.get<BaseResponse<PaginatedList<Group>>>(url, { params: dto });
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

  getGroupsDDL(): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-all-groups-ddl');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }

  getGroupDisciplinesDDL(groupId: number): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-group-disciplines-ddl');
    let params = { groupId: groupId };
    return this.http.get<BaseResponse<DDL<number>[]>>(url, { params: params });
  }

  getAllStudentsWithoutGroup(): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-students-without-group');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }

  getStudentsByGroup(groupId: number): Observable<BaseResponse<Student[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-students-by-group');
    let param = { groupId: groupId }
    return this.http.get<BaseResponse<Student[]>>(url, { params: param });
  }

  getStudentsByGroupDDL(groupId: number): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-all-students-from-group-ddl');
    let param = { groupId: groupId }
    return this.http.get<BaseResponse<DDL<number>[]>>(url, { params: param });
  }

  attachStudent(dto: AttachStudentDTO): Observable<BaseResponse<any>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'attach-student');
    return this.http.post<BaseResponse<any>>(url, dto);
  }

  detachStudent(groupId: number, studentId: number) {
    let url: string = PrepareApi.prepare(this.controllerName, 'detach-student');
    let params = { groupId: groupId, studentId: studentId };
    return this.http.delete<BaseResponse<any>>(url, { params: params });
  }

  getGroupDisciplines(groupId: number): Observable<BaseResponse<GroupDiscipline[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-group-teacher-discipline');
    let params = { groupId: groupId };
    return this.http.get<BaseResponse<GroupDiscipline[]>>(url, { params: params });
  }

  attachGroupDiscipline(dto: GroupDisciplineDTO): Observable<BaseResponse<GroupDiscipline>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'attach-teacher-discipline');
    return this.http.post<BaseResponse<GroupDiscipline>>(url, dto);
  }

  detachGroupDiscipline(dto: GroupDisciplineDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, 'detach-teacher-discipline');
    return this.http.delete(url, { body: dto });
  }

  getTeachersOfGroupDisciplineDDL(groupId: number, disciplineId: number): Observable<BaseResponse<DDL<number>[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'get-teachers-of-group-discipline-ddl');
    let params = { groupId: groupId, disciplineId: disciplineId };
    return this.http.get<BaseResponse<DDL<number>[]>>(url, { params: params });
  }
}
