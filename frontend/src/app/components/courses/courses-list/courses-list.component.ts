import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CourseService } from 'src/app/services/courses/course.service';
import { Course } from 'src/app/utils/typings/course';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit {

  constructor(private service: CourseService, private route: Router, private toaster: ToastrService) { }

  courseList: Course[] = []

  ngOnInit(): void {
    this.service.findAll().subscribe(response => {
      this.courseList = response;
    })
  }

  updateCourse(id: number) {
    this.route.navigate(['courses', id])
  }

  deleteCourse(id: number) {
    this.service.delete(id).subscribe(data => {
      this.toaster.success('Registro deletado com sucesso!');
      this.service.findAll().subscribe(data => {
        this.courseList = data;
        setTimeout(() => {
          this.route.navigate(['courses']);
        });
      })
    }, error => {
      this.toaster.error('Ocorreu um erro ao deletar registro selecionado');
    });
  }

  createCourse() {
    this.route.navigate(['courses/create']);
  }

}
