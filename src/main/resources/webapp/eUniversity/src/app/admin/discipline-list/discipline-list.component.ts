import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateDisciplineDTO } from 'src/app/core/DTOs/admin/discipline/update-discipline-dto';
import { Discipline } from 'src/app/core/models/admin/discipline';
import { BaseResponse } from 'src/app/core/models/base/base-response';
import { DisciplineService } from 'src/app/services/discipline.service';

@Component({
  selector: 'app-discipline-list',
  templateUrl: './discipline-list.component.html',
  styleUrls: ['../../shared/table-block.scss', '../../shared/modal.scss', '../../shared/input.scss']
})
export class DisciplineListComponent implements OnInit {

  isAddFormVisible: boolean;
  isEditFormVisible: boolean;

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
    this.isAddFormVisible = false;
    this.isEditFormVisible = false;

    this.addForm = new FormGroup({
      name: new FormControl('', Validators.required),
      shortName: new FormControl('', Validators.required)
    });

    this.editForm = new FormGroup({
      name: new FormControl('', Validators.required),
      shortName: new FormControl('', Validators.required)
    });

    this.disciplines = [];
  }

  ngOnInit(): void {
    this.disciplineService.getDisciplines().subscribe((res: BaseResponse<Discipline[]>) => {
      this.disciplines = res.data;
    });
  }

  showAddForm() {
    this.isAddFormVisible = true;

    this.editForm.get('name')!.setValue('');
    this.editForm.get('shortName')!.setValue('');
  }

  showEditForm(discipline: Discipline) {
    this.isEditFormVisible = true;
    this.selectedDiscipline = discipline;

    this.editForm.get('name')!.setValue(discipline.name);
    this.editForm.get('shortName')?.setValue(discipline.shortName);
  }

  closeEditForm() {
    this.isEditFormVisible = false;
  }

  closeAddForm() {
    this.isAddFormVisible = false;
  }

  addDiscipline() {
    if (this.addForm.valid)
      this.disciplineService.addDiscipline(this.addForm.value).subscribe({
        next: (data: BaseResponse<number>) => {
          let discipline: Discipline = new Discipline(data.data, this.addName?.value, this.addShortName?.value);
          this.disciplines.unshift(discipline);

          this.isAddFormVisible = false;
        },
        error: (data) => {
          alert('can`t add discipline with same name');
        }
      });
  }

  updateDiscipline() {
    if (this.editForm.valid) {
      let dto: UpdateDisciplineDTO = new UpdateDisciplineDTO(this.selectedDiscipline.id, this.editName?.value, this.editShortName?.value);

      this.disciplineService.updateDiscipline(dto).subscribe({
        next: (data) => {
          this.isEditFormVisible = false;
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
          if (this.disciplines[i].id == id) {
            this.disciplines.splice(i, 1);
            break;
          }
        }
      },
      error: (data) => {
        alert('cant remove this discipline');
      }
    })
  }

}
