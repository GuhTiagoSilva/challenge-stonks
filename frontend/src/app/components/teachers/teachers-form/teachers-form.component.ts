import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TeacherService } from 'src/app/services/teachers/teacher.service';
import { Teacher } from 'src/app/utils/typings/teacher';

@Component({
  selector: 'app-teachers-form',
  templateUrl: './teachers-form.component.html',
  styleUrls: ['./teachers-form.component.css']
})
export class TeachersFormComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private service: TeacherService,
    private toaster: ToastrService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  teacherForm: FormGroup;
  id: number;


  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.initForm();

    if (this.id) {
      this.findById();
    }
  }

  private findById() {
    this.service.findById(this.id).subscribe(response => {
      this.setFormData(response);
    });
  }

  private initForm() {
    this.teacherForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(5)]],
      lastName: ['', [Validators.required, Validators.minLength(5)]],
      bornDate: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      roleId: [0],
      yearsOfExperience: ['', Validators.required]
    });
  }

  private setFormData(data: Teacher) {
    this.teacherForm = this.formBuilder.group({
      firstName: [data.firstName, [Validators.required, Validators.minLength(5)]],
      lastName: [data.lastName, [Validators.required, Validators.minLength(5)]],
      bornDate: [data.bornDate, Validators.required],
      email: [data.email, [Validators.required, Validators.email]],
      password: ['', []],
      roleId: [0],
      yearsOfExperience: [data.yearsOfExperience, Validators.required]
    });
  }

  onSubmit() {
    this.setRole();

    if (!this.id) {
      if (this.teacherForm.valid) {

        const date = new Date(this.teacherForm.get('bornDate').value);
        this.teacherForm.get('bornDate').setValue(date);

        this.service.create(this.teacherForm.value).subscribe(data => {
          this.toaster.success('Cadastro realizado com sucesso!');
          setTimeout(() => {
            this.route.navigate(['teachers']);
          }, 1000);
        }, error => {
          this.toaster.error('Ocorreu um erro ao salvar!')
        });
      } else {
        this.toaster.error('Preencha os campos obrigatórios')
      }
    } else {
      if (this.teacherForm.valid) {

        const date = new Date(this.teacherForm.get('bornDate').value);
        this.teacherForm.get('bornDate').setValue(date);

        this.service.update(this.id, this.teacherForm.value).subscribe(data => {
          this.toaster.success('Cadastro realizado com sucesso!');
          setTimeout(() => {
            this.route.navigate(['teachers']);
          }, 1000);
        }, error => {
          this.toaster.error('Ocorreu um erro ao salvar!')
        });
      } else {
        this.toaster.error('Preencha os campos obrigatórios')
      }
    }
  }

  private setRole() {
    this.teacherForm.get('roleId').setValue(2);
  }

}
