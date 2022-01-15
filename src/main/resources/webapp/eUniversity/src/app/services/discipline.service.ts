import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddDisciplineDTO } from '../core/DTOs/admin/add-discipline-dto';
import { UpdateDisciplineDTO } from '../core/DTOs/admin/update-discipline-dto';
import { PaginationDTO } from '../core/DTOs/pagination';
import { Discipline } from '../core/models/admin/discipline';
import { BaseResponse } from '../core/models/base/base-response';
import { DDL } from '../core/models/ddl';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class DisciplineService {

  private readonly controllerName: string = 'admin-panel/discipline';


  constructor(
    private http: HttpClient
  ) { }

  addDiscipline(discipline: AddDisciplineDTO): Observable<BaseResponse<number>> {
    let url = PrepareApi.prepare(this.controllerName, 'add-discipline');
    return this.http.post<BaseResponse<number>>(url, discipline);
  }

  getDisciplines(dto: PaginationDTO): Observable<BaseResponse<Discipline[]>> {
    let url = PrepareApi.prepare(this.controllerName, 'get-all-disciplines');
    let params = { search: dto.search, index: dto.index, size: dto.size };
    return this.http.get<BaseResponse<Discipline[]>>(url, { params: params });
  }

  getDisciplinesDLL(): Observable<BaseResponse<DDL<number>[]>> {
    let url = PrepareApi.prepare(this.controllerName, 'get-disciplines-dll');
    return this.http.get<BaseResponse<DDL<number>[]>>(url);
  }

  updateDiscipline(dto: UpdateDisciplineDTO) {
    let url = PrepareApi.prepare(this.controllerName, 'update-discipline');
    return this.http.put<BaseResponse<any>>(url, dto);
  }

  deleteDiscipline(id: number) {
    let url = PrepareApi.prepare(this.controllerName, 'delete-discipline');
    let params = { id: id }
    return this.http.delete(url, { params: params });
  }
}
