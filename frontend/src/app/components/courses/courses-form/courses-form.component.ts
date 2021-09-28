import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CourseService } from 'src/app/services/courses/course.service';
import { Course } from 'src/app/utils/typings/course';

@Component({
  selector: 'app-courses-form',
  templateUrl: './courses-form.component.html',
  styleUrls: ['./courses-form.component.css']
})
export class CoursesFormComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private service: CourseService,
    private toaster: ToastrService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  courseForm: FormGroup;
  id: number;

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params["id"];
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
    this.courseForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      duration: [null, Validators.required]
    });
  }

  private setFormData(data: Course) {
    this.courseForm.get('name').setValue(data.name);
    this.courseForm.get('description').setValue(data.description);
    this.courseForm.get('duration').setValue(data.duration);
  }

  onSubmit() {
    if (!this.id) {
      if (this.courseForm.valid) {
        this.createCourse();
      } else {
        this.toaster.error('Preencha os campos obrigatórios');
      }
    } else if (this.id) {
      if (this.courseForm.valid) {
        this.updateCourse();
      } else {
        this.toaster.error('Preencha os campos obrigatórios');
      }
    }
  }

  private updateCourse() {
    this.service.update(this.id, this.courseForm.value).subscribe(response => {
      this.toaster.success('Registro atualizado com sucesso!');
      setTimeout(() => {
        this.route.navigate(['courses']);
      }, 1000);
    }, error => {
      this.toaster.error('Ocorreu um erro ao salvar!');
    });
  }

  private createCourse() {
    this.service.create(this.courseForm.value).subscribe(data => {
      this.toaster.success('Cadastro realizado com sucesso!');
      setTimeout(() => {
        this.route.navigate(['courses']);
      }, 1000);
    }, error => {
      this.toaster.error('Ocorreu um erro ao salvar!');
    });
  }

}
