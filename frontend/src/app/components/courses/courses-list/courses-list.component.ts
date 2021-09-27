import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/services/courses/course.service';
import { Course } from 'src/app/utils/typings/course';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit {

  constructor(private service: CourseService) { }

  courseList: Course[] = []

  ngOnInit(): void {
    this.service.findAll().subscribe(response => {
      this.courseList = response;
    })
  }

}
