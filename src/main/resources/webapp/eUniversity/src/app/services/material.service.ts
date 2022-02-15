import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MaterialFile } from '../core/models/admin/material-file';
import { BaseResponse } from '../core/models/base/base-response';
import { PrepareApi } from './prepare-api';
import { saveAs } from 'file-saver';


@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  private readonly controllerName: string = 'admin-panel/materials';

  constructor(
    private http: HttpClient
  ) { }

  uploadMaterial(formData: any) {
    let url: string = PrepareApi.prepare(this.controllerName, 'upload-file');
    return this.http.post(url, formData);
  }

  getEduMaterials(groupId: number, disciplineId: number): Observable<BaseResponse<MaterialFile[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'educational');
    let params = { groupId: groupId, disciplineId: disciplineId };
    return this.http.get<BaseResponse<MaterialFile[]>>(url, { params: params });
  }

  getStudentMaterials(groupId: number, disciplineId: number, studentId: number): Observable<BaseResponse<MaterialFile[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'posted-by-student');
    let params = { groupId: groupId, disciplineId: disciplineId, studentId: studentId };
    return this.http.get<BaseResponse<MaterialFile[]>>(url, { params: params });
  }

  //guid
  //responseType: 'arraybuffer'
  //file-saver npm
  downloadFile(materialId: number) {
    let url: string = PrepareApi.prepare(this.controllerName, 'download-file');
    let params = { materialId: materialId };
    return this.http.get(url, { params: params, responseType: 'blob' });

    //return this.http.get(url, { params: params });

    //   get(url: string, options: {
    //     headers?: HttpHeaders | {
    //         [header: string]: string | string[];
    //     };
    //     context?: HttpContext;
    //     observe?: 'body';
    //     params?: HttpParams | {
    //         [param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>;
    //     };
    //     reportProgress?: boolean;
    //     responseType: 'blob';
    //     withCredentials?: boolean;
    // }): Observable<Blob>;
  }

  removeFile(materialId: number) {
    let url: string = PrepareApi.prepare(this.controllerName, 'remove');
    let params = { materialId: materialId };
    return this.http.delete(url, { params: params });
  }

}
