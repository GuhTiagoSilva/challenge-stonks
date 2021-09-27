import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastyConfig, ToastyService } from 'ng2-toasty';
import { ToastrService } from 'ngx-toastr';
import { StudentService } from 'src/app/services/students/student.service';

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
    private route: Router
    ) {

  }

  studentForm: FormGroup;

  ngOnInit(): void {
    this.studentForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      bornDate: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      roleId: [0],
      registrationCode: ['', Validators.required]
    });
  }

  onSubmit() {

    this.setRole();

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
  }

  setRole() {
    this.studentForm.get('roleId').setValue(1);
  }

}
