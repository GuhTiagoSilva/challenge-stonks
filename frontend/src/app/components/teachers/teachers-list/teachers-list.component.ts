import { Component, OnInit } from '@angular/core';
import { TeacherService } from 'src/app/services/teachers/teacher.service';
import { Teacher } from 'src/app/utils/typings/teacher';

@Component({
  selector: 'app-teachers-list',
  templateUrl: './teachers-list.component.html',
  styleUrls: ['./teachers-list.component.css']
})
export class TeachersListComponent implements OnInit {

  constructor(private service: TeacherService) { }

  teacherList: Teacher[] = [];

  ngOnInit(): void {
    this.service.findAll().subscribe(data => {
      this.teacherList = data;
    });
  }

}
