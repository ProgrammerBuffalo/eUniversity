import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateDisciplineDTO } from 'src/app/core/DTOs/admin/update-discipline-dto';
import { PaginationDTO } from 'src/app/core/DTOs/pagination';
import { Discipline } from 'src/app/core/models/admin/discipline';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DisciplineService } from 'src/app/services/discipline.service';

@Component({
  selector: 'app-discipline-list',
  templateUrl: './discipline-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class DisciplineListComponent implements OnInit {

  pagination: PaginationDTO;

  showAddPopup: boolean;
  showEditPopup: boolean;

  selectedDiscipline!: Discipline;
  disciplines: Discipline[];

  addForm: FormGroup;
  editForm: FormGroup;

  get addName() { return this.addForm.get('name'); }

  get addShortName() { return this.addForm.get('shortName'); }

  get editName() { return this.editForm.get('name'); }

  get editShortName() { return this.editForm.get('shortName'); }

  constructor(
    private disciplineService: DisciplineService
  ) {
    this.showAddPopup = false;
    this.showEditPopup = false;

    this.addForm = new FormGroup({
      name: new FormControl('', Validators.required),
      shortName: new FormControl('', Validators.required)
    });

    this.editForm = new FormGroup({
      name: new FormControl('', Validators.required),
      shortName: new FormControl('', Validators.required)
    });

    this.disciplines = [];
    this.pagination = new PaginationDTO('', 0, 5);
  }

  ngOnInit(): void {
    this.disciplineService.getDisciplines(this.pagination).subscribe((res: BaseResponse<Discipline[]>) => {
      this.disciplines = res.data;
    });
  }

  showAddModal() {
    this.showAddPopup = true;

    this.editForm.get('name')!.setValue('');
    this.editForm.get('shortName')!.setValue('');
  }

  showEditModal(discipline: Discipline) {
    this.showEditPopup = true;
    this.selectedDiscipline = discipline;

    console.log(discipline);

    this.editForm.get('name')!.setValue(discipline.name);
    this.editForm.get('shortName')?.setValue(discipline.shortName);
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
        next: (data: BaseResponse<number>) => {
          let discipline: Discipline = new Discipline(data.data, this.addName?.value, this.addShortName?.value);
          this.disciplines.unshift(discipline);

          this.showAddPopup = false;
        },
        error: (data) => {
          alert('can`t add discipline with same name');
        }
      });
  }

  updateDiscipline() {
    if (this.editForm.valid) {
      let dto: UpdateDisciplineDTO = new UpdateDisciplineDTO(this.selectedDiscipline.id, this.editName?.value, this.editShortName?.value);

      console.log(dto);

      this.disciplineService.updateDiscipline(dto).subscribe({
        next: (data) => {
          this.showEditPopup = false;
          this.selectedDiscipline.name = dto.name;
          this.selectedDiscipline.shortName = dto.shortName;
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
