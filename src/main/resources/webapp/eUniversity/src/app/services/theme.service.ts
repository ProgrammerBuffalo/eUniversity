import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddThemeDTO } from '../core/DTOs/admin/theme/add-theme';
import { UpdateThemeDTO } from '../core/DTOs/admin/theme/update-theme';
import { Theme } from '../core/models/admin/theme';
import { BaseResponse } from '../core/models/base/base-response';
import { PrepareApi } from './prepare-api';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private readonly controllerName: string = 'admin-panel/theme';

  constructor(
    private http: HttpClient
  ) { }

  addTheme(dto: AddThemeDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, '');
    return this.http.post(url, dto);
  }

  getThemes(groupId: number, disciplineId: number): Observable<BaseResponse<Theme[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, '');
    let params = { groupId: groupId, disciplineId: disciplineId };
    return this.http.get<BaseResponse<Theme[]>>(url, { params: params });
  }

  updateTheme(dto: UpdateThemeDTO) {
    let url: string = PrepareApi.prepare(this.controllerName, '');
    return this.http.put(url, dto);
  }

  removeTheme(id: number) {
    let url: string = PrepareApi.prepare(this.controllerName, '');
    let params = { id: id };
    return this.http.delete(url, { params: params });
  }
}
