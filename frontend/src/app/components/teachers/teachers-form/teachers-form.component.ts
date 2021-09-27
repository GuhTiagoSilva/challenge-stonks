import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TeacherService } from 'src/app/services/teachers/teacher.service';

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
    private route: Router
  ) { }

  teacherForm: FormGroup;

  ngOnInit(): void {
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

  onSubmit() {
    this.setRole();

    if (this.teacherForm.valid) {

      const date = new Date(this.teacherForm.get('bornDate').value);
      this.teacherForm.get('bornDate').setValue(date);

      this.service.create(this.teacherForm.value).subscribe(data => {
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
  }

  setRole() {
    this.teacherForm.get('roleId').setValue(3);
  }

}
