import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { StudentService } from 'src/app/services/students/student.service';
import { Student } from 'src/app/utils/typings/student';

@Component({
  selector: 'app-students-form',
  templateUrl: './students-form.component.html',
  styleUrls: ['./students-form.component.css']
})
export class StudentsFormComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private service: StudentService,
    private toaster: ToastrService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) {

  }

  studentForm: FormGroup;
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
    this.studentForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      bornDate: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      roleId: [0],
      registrationCode: ['', [Validators.required, Validators.minLength(2)]]
    });
  }

  private setFormData(data: Student) {
    this.studentForm = this.formBuilder.group({
      firstName: [data.firstName, [Validators.required, Validators.minLength(2)]],
      lastName: [data.lastName, [Validators.required, Validators.minLength(2)]],
      bornDate: [data.bornDate, Validators.required],
      email: [data.email, [Validators.required, Validators.email]],
      password: ['', []],
      roleId: [0],
      registrationCode: [data.registrationCode, [Validators.required, Validators.minLength(2)]]
    });
  }

  onSubmit() {

    this.setRole();

    if (!this.id) {
      if (this.studentForm.valid) {
        const date = new Date(this.studentForm.get('bornDate').value);
        this.studentForm.get('bornDate').setValue(date);

        this.service.create(this.studentForm.value).subscribe(data => {
          this.toaster.success('Cadastro realizado com sucesso!');
          setTimeout(() => {
            this.route.navigate(['students']);
          }, 1000);
        }, error => {
          this.toaster.error('Ocorreu um erro ao salvar!')
        });
      } else {
        this.toaster.error('Preencha os campos obrigatórios')
      }
    } else {
      if (this.studentForm.valid) {

        const date = new Date(this.studentForm.get('bornDate').value);
        this.studentForm.get('bornDate').setValue(date);

        this.service.update(this.id, this.studentForm.value).subscribe(data => {
          this.toaster.success('Registro atualizado com sucesso!');
          setTimeout(() => {
            this.route.navigate(['students']);
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
    this.studentForm.get('roleId').setValue(1);
  }

}
