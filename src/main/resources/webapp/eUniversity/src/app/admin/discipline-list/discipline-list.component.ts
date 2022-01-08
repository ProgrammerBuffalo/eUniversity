import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateDisciplineDTO } from 'src/app/core/DTOs/admin/update-discipline-dto';
import { Discipline } from 'src/app/core/models/admin/discipline';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DisciplineService } from 'src/app/services/discipline.service';

@Component({
  selector: 'app-discipline-list',
  templateUrl: './discipline-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class DisciplineListComponent implements OnInit {

  showAddPopup: boolean;
  showEditPopup: boolean;

  selectedDiscipline!: Discipline;
  disciplines: Discipline[];

  addForm: FormGroup;
  editForm: FormGroup;

  get addName() { return this.addForm.get('name'); }

  get editName() { return this.editForm.get('name'); }

  constructor(
    private disciplineService: DisciplineService
  ) {
    this.showAddPopup = false;
    this.showEditPopup = false;

    this.addForm = new FormGroup({
      name: new FormControl('', Validators.required),
    });

    this.editForm = new FormGroup({
      name: new FormControl('', Validators.required),
    });

    this.disciplines = [];
  }

  ngOnInit(): void {
    this.disciplineService.getDisciplines().subscribe((data: BaseResponse<Discipline[]>) => {
      this.disciplines = data.data;
    });
  }

  showAddModal() {
    this.showAddPopup = true;

    this.editForm.get('name')!.setValue('');
  }

  showEditModal(discipline: Discipline) {
    this.showEditPopup = true;
    this.selectedDiscipline = discipline;

    this.editForm.get('name')!.setValue(discipline.name);
  }

  closeEditModal() {
    this.showEditPopup = false;
  }

  closeAddModal() {
    this.showAddPopup = false;
  }

  addDiscipline() {
    if (this.addForm.valid)
      this.disciplineService.addDiscipline(this.addForm.value).subscribe({
        next: (data: BaseResponse<Discipline>) => {
          this.disciplines.unshift(data.data);
        },
        error: (data) => {
          alert('can`t add discipline');
        }
      });
  }

  updateDiscipline() {
    if (this.editForm.valid) {
      let dto: UpdateDisciplineDTO = new UpdateDisciplineDTO(this.selectedDiscipline.id, this.editName?.value);

      this.disciplineService.updateDiscipline(dto).subscribe({
        next: (data) => {
          this.selectedDiscipline.name = this.editName?.value;
        },
        error: (data) => {
          alert('this discipline alredy exsists');
        }
      });
    }
  }

  removeDiscipline(id: number) {
    this.disciplineService.deleteDiscipline(id).subscribe({
      next: (data) => {
        for (let i = 0; i < this.disciplines.length; i++) {
          if (this.disciplines[i].id == id)
            this.disciplines.splice(i, 1);
        }
      },
      error: (data) => {
        alert('cant remove this discipline');
      }
    })
  }

}
