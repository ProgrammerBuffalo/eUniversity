import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddDisciplineDTO } from '../core/DTOs/admin/add-discipline-dto';
import { UpdateDisciplineDTO } from '../core/DTOs/admin/update-discipline-dto';
import { Discipline } from '../core/models/admin/discipline';
import { BaseResponse } from '../core/models/base/base-response';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class DisciplineService {

  private readonly controllerName: string = '';


  constructor(
    private http: HttpClient
  ) { }

  addDiscipline(discipline: AddDisciplineDTO): Observable<BaseResponse<any>> {
    let url = PrepareApi.prepare(this.controllerName, '');
    return this.http.post<BaseResponse<any>>(url, discipline);
  }

  getDisciplines(): Observable<BaseResponse<Discipline[]>> {
    let url = PrepareApi.prepare(this.controllerName, '');
    return this.http.get<BaseResponse<Discipline[]>>(url);
  }

  updateDiscipline(dto: UpdateDisciplineDTO) {
    let url = PrepareApi.prepare(this.controllerName, '');
    return this.http.put<BaseResponse<any>>(url, dto);
  }

  deleteDiscipline(id: number) {
    let url = PrepareApi.prepare(this.controllerName, '');
    let params = { id: id }
    return this.http.delete(url, { params: params });
  }
}
