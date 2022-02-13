import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EduMaterial } from '../core/models/admin/material/edu-material';
import { StudentMaterial } from '../core/models/admin/material/student-material';
import { BaseResponse } from '../core/models/base/base-response';
import { PrepareApi } from './prepare-api';


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

  getEduMaterials(groupId: number, disciplineId: number): Observable<BaseResponse<EduMaterial[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'educational');
    let params = { groupId: groupId, disciplineId: disciplineId };
    return this.http.get<BaseResponse<EduMaterial[]>>(url, { params: params });
  }

  getStudentMaterials(groupId: number, disciplineId: number, studentId: number): Observable<BaseResponse<EduMaterial[]>> {
    let url: string = PrepareApi.prepare(this.controllerName, 'posted-by-student');
    let params = { groupId: groupId, disciplineId: disciplineId, studentId: studentId };
    return this.http.get<BaseResponse<EduMaterial[]>>(url, { params: params });
  }

  //guid
  //responseType: 'arraybuffer'
  //file-saver npm
  downloadFile(fileId: string) {
    let url: string = PrepareApi.prepare(this.controllerName, 'download-file');
    let params = { fileId: fileId };
    return this.http.get<BaseResponse<StudentMaterial[]>>(url, { params: params });
  }

  removeFile(id: number) {
    let url: string = PrepareApi.prepare(this.controllerName, 'remove');
    let params = { id: id };
    return this.http.delete(url, { params: params });
  }

  // removeStudentFile(id: number) {
  //   let url: string = PrepareApi.prepare(this.controllerName, 'remove2');
  //   let params = { id: id };
  //   return this.http.delete(url, { params: params });
  // }
}
