
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AttachDisciplineDTO } from 'src/app/core/DTOs/admin/attach-discipline-dto';
import { TeacherDiscipline } from 'src/app/core/models/admin/teacher-discipline';
import { TeacherShortDisciplines } from 'src/app/core/models/admin/teacher-short-disciplines';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { refreshSelectPicker } from 'src/app/core/util/select-picker';
import { DisciplineService } from 'src/app/services/discipline.service';
import { TeacherService } from 'src/app/services/teacher.service';


@Component({
  selector: 'app-teacher-discipline-list',
  templateUrl: './teacher-discipline-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class TeacherDisciplineListComponent implements OnInit {

  showEditPopup: boolean;
  teachersShortDisciplines!: TeacherShortDisciplines[];
  teacherDisciplines!: TeacherDiscipline[];

  selectedTeacherDisciplines!: TeacherShortDisciplines;

  disciplines!: DDL<number>[];

  editForm: FormGroup;

  constructor(
    private teacherService: TeacherService,
    private disciplineService: DisciplineService
  ) {
    this.showEditPopup = false;

    this.editForm = new FormGroup({
      disciplineId: new FormControl(0, Validators.required)
    });

  }

  get editDisciplineId(): number { return this.editForm.get('disciplineId')?.value }

  ngOnInit(): void {
    this.teacherService.getTeachersShortDisciplines().subscribe((res: BaseResponse<TeacherShortDisciplines[]>) => {
      this.teachersShortDisciplines = res.data;
    });

    this.disciplineService.getDisciplinesDLL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.disciplines = res.data;
      refreshSelectPicker();
    });
  }

  showEditModal(teacherShorDiscipline: TeacherShortDisciplines) {
    this.showEditPopup = true;

    this.selectedTeacherDisciplines = teacherShorDiscipline;

    this.teacherService.getTeacherDisciplines(this.selectedTeacherDisciplines.id).subscribe((res: BaseResponse<TeacherDiscipline[]>) => {
      this.teacherDisciplines = res.data;
    });
  }

  closeEditModal() {
    this.showEditPopup = false;
  }

  attachDiscipline() {
    let dto: AttachDisciplineDTO = new AttachDisciplineDTO(this.selectedTeacherDisciplines.id, this.editDisciplineId);

    this.teacherService.attachDiscipline(dto).subscribe({
      next: (res: any) => {
        this.teacherService.getTeacherDisciplines(this.selectedTeacherDisciplines.id).subscribe((res: BaseResponse<TeacherDiscipline[]>) => {
          this.teacherDisciplines = res.data;
        });

        this.teacherService.getTeacherShortDisciplines(this.selectedTeacherDisciplines.id).subscribe((res: BaseResponse<TeacherShortDisciplines>) => {
          this.selectedTeacherDisciplines.shortDisciplines = res.data.shortDisciplines;
        });
      },
      error: (res: any) => {
        alert('can`t attach same discipline to teacher');
      }
    });
  }

  detachDiscipline(disciplineId: number) {
    this.teacherService.detachDiscipline(this.selectedTeacherDisciplines.id, disciplineId).subscribe((res: any) => {
      for (let i = 0; i < this.teacherDisciplines.length; i++) {
        if (this.teacherDisciplines[i].id == disciplineId)
          this.teacherDisciplines.splice(i, 1);
      }

      this.teacherService.getTeacherShortDisciplines(this.selectedTeacherDisciplines.id).subscribe((res: BaseResponse<TeacherShortDisciplines>) => {
        this.selectedTeacherDisciplines.shortDisciplines = res.data.shortDisciplines;
      });
    });
  }

}
