declare var $: any;

import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AttachDisciplineDTO } from 'src/app/core/DTOs/admin/attach-discipline-dto';
import { TeacherDiscipline } from 'src/app/core/models/admin/teacher-discipline';
import { TeacherShortDisciplines } from 'src/app/core/models/admin/teacher-short-disciplines';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DDL } from 'src/app/core/models/ddl';
import { DisciplineService } from 'src/app/services/discipline.service';
import { TeacherDisciplineService } from 'src/app/services/teacher-discipline.service';
import 'bootstrap-select';

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
  // selectedDisciplineId!: number;

  disciplines!: DDL<number>[];

  editForm: FormGroup;

  constructor(
    private teacherDisciplineService: TeacherDisciplineService,
    private disciplineService: DisciplineService
  ) {


    this.showEditPopup = false;

    this.editForm = new FormGroup({
      disciplineId: new FormControl(0, Validators.required)
    });

  }



  get editDisciplineId(): number { return this.editForm.get('disciplineId')?.value }

  ngOnInit(): void {
    // this.teacherDisciplineService.getTeachersShortDisciplines().subscribe((data: BaseResponse<TeacherShortDisciplines[]>) => {
    //   console.log(data)
    // });

    // this.teacherDisciplineService.getTeacherDisciplines(4).subscribe((data: BaseResponse<TeacherDiscipline[]>) => {
    //   console.log(data)
    // });

    // this.teacherDisciplineService.getTeacherShortDisciplines(4).subscribe((data: BaseResponse<TeacherShortDisciplines>) => {
    //   console.log(data)
    // });

    this.teacherDisciplineService.getTeachersShortDisciplines().subscribe((res: BaseResponse<TeacherShortDisciplines[]>) => {
      this.teachersShortDisciplines = res.data;
    });

    this.disciplineService.getDisciplinesDLL().subscribe((res: BaseResponse<DDL<number>[]>) => {
      this.disciplines = res.data;
    });
  }

  ngAfterContentChecked() {
    $('.selectpicker').selectpicker();
  }

  showEditModal(teacherShorDiscipline: TeacherShortDisciplines) {
    this.showEditPopup = true;

    this.selectedTeacherDisciplines = teacherShorDiscipline;

    this.teacherDisciplineService.getTeacherDisciplines(this.selectedTeacherDisciplines.id).subscribe((res: BaseResponse<TeacherDiscipline[]>) => {
      console.log(res);
      this.teacherDisciplines = res.data;
    });
  }

  closeEditModal() {
    this.showEditPopup = false;
  }

  attachDiscipline() {
    let dto: AttachDisciplineDTO = new AttachDisciplineDTO(this.selectedTeacherDisciplines.id, this.editDisciplineId);

    this.teacherDisciplineService.attachDiscipline(dto).subscribe((res: any) => {

      let disciplineName: string = '';

      for (let i = 0; i < this.disciplines.length; i++) {
        if (this.disciplines[i].id == this.editDisciplineId) {
          disciplineName = this.disciplines[i].name;
          break;
        }
      }

      this.teacherDisciplines.unshift(new TeacherDiscipline(this.editDisciplineId, disciplineName));

      this.teacherDisciplineService.getTeacherShortDisciplines(this.selectedTeacherDisciplines.id).subscribe((res: BaseResponse<TeacherShortDisciplines>) => {
        this.selectedTeacherDisciplines.shortDisciplines = res.data.shortDisciplines;
      });
    });
  }

  detachDiscipline(disciplineId: number) {
    this.teacherDisciplineService.detachDiscipline(this.selectedTeacherDisciplines.id, disciplineId).subscribe((res: any) => {
      for (let i = 0; i < this.teacherDisciplines.length; i++) {
        if (this.teacherDisciplines[i].id == disciplineId)
          this.teacherDisciplines.splice(i, 1);
      }

      this.teacherDisciplineService.getTeacherShortDisciplines(this.selectedTeacherDisciplines.id).subscribe((res: BaseResponse<TeacherShortDisciplines>) => {
        this.selectedTeacherDisciplines.shortDisciplines = res.data.shortDisciplines;
      });
    });
  }

  // disciplineChange(discipline: DDL<number>) {

  // }

  // showAddPopup: boolean;
  // showEditPopup: boolean;

  // selectedStudent!: Student;
  // students: Student[];

  // addForm: FormGroup;
  // editForm: FormGroup;

  // get addLogin() { return this.addForm.get('login'); }

  // get addPassword() { return this.addForm.get('password'); }

  // get addFullName() { return this.addForm.get('fullName'); }

  // get addAge() { return this.addForm.get('age'); }

  // get editLogin() { return this.editForm.get('login'); }

  // get editFullName() { return this.editForm.get('fullName'); }

  // get editAge() { return this.editForm.get('age'); }

  // constructor(
  //   private accountService: AccountService,
  //   private groupService: GroupService
  // ) {
  //   this.showAddPopup = false;
  //   this.showEditPopup = false;

  //   this.addForm = new FormGroup({
  //     login: new FormControl('', Validators.required),
  //     password: new FormControl('', Validators.required),
  //     fullName: new FormControl('', Validators.required),
  //     age: new FormControl('', [Validators.required, Validators.min(16), Validators.max(100)])
  //   });

  //   this.editForm = new FormGroup({
  //     login: new FormControl('', Validators.required),
  //     fullName: new FormControl('', Validators.required),
  //     age: new FormControl('', [Validators.required, Validators.min(16), Validators.max(100)])
  //   });

  //   this.students = [];
  // }

  // ngOnInit(): void {
  //   this.accountService.getStudents().subscribe((data: BaseResponse<Student[]>) => {
  //     this.students = data.data;
  //   });
  // }

  // showAddModal() {
  //   this.showAddPopup = true;

  //   this.editForm.get('login')!.setValue('');
  //   this.editForm.get('password')!.setValue('');
  //   this.editForm.get('fullName')!.setValue('');
  //   this.editForm.get('age')!.setValue('');
  // }

  // showEditModal(student: Student) {
  //   console.log(student);
  //   this.showEditPopup = true;
  //   this.selectedStudent = student;

  //   this.editForm.get('login')!.setValue(student.login);
  //   this.editForm.get('fullName')!.setValue(student.fullName);
  //   this.editForm.get('age')!.setValue(student.age);
  // }

  // closeEditModal() {
  //   this.showEditPopup = false;
  // }

  // closeAddModal() {
  //   this.showAddPopup = false;
  // }

  // addStudent() {
  //   if (this.addForm.valid)
  //     this.accountService.registerStudent(this.addForm.value).subscribe({
  //       next: (data: BaseResponse<Student>) => {
  //         this.students.unshift(data.data);

  //         this.showAddPopup = false;
  //       },
  //       error: (data) => {
  //         alert('can`t add student');
  //       }
  //     });
  // }

  // updateStudent() {
  //   if (this.editForm.valid) {
  //     let dto: UpdateStudentDTO = new UpdateStudentDTO(
  //       this.selectedStudent.id, this.editLogin?.value, this.editFullName?.value, this.editAge?.value);

  //     this.accountService.updateStudent(dto).subscribe({
  //       next: (data) => {
  //         this.selectedStudent.login = this.editLogin?.value;
  //         this.selectedStudent.fullName = this.editFullName?.value;
  //         this.selectedStudent.age = this.editAge?.value;

  //         this.showEditPopup = false;
  //       },
  //       error: (data) => {
  //         alert('this login alredy exsists');
  //       }
  //     });
  //   }
  // }

  // removeStudent(id: string) {
  //   this.accountService.deleteStudent(id).subscribe({
  //     next: (data) => {
  //       for (let i = 0; i < this.students.length; i++) {
  //         if (this.students[i].id == id)
  //           this.students.splice(i, 1);
  //       }
  //     },
  //     error: (data) => {
  //       alert('cant remove this student');
  //     }
  //   })
  // }

}
