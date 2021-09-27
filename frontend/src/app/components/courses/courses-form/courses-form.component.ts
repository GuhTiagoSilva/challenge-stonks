import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CourseService } from 'src/app/services/courses/course.service';

@Component({
  selector: 'app-courses-form',
  templateUrl: './courses-form.component.html',
  styleUrls: ['./courses-form.component.css']
})
export class CoursesFormComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private service: CourseService, private toaster: ToastrService, private route: Router) { }

  courseForm: FormGroup;

  ngOnInit(): void {
    this.courseForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      duration: [null, Validators.required]
    }
    );
  }

  onSubmit() {
    if (this.courseForm.valid) {
      this.service.create(this.courseForm.value).subscribe(data => {
        this.toaster.success('Cadastro realizado com sucesso!');
        setTimeout(() => {
          this.route.navigate(['courses']);
        }, 1000);
      }, error => {
        this.toaster.error('Ocorreu um erro ao salvar!');
      });
    } else {
      this.toaster.error('Preencha os campos obrigatórios');
    }
  }

}
