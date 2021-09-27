import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/students/student.service';
import { Student } from 'src/app/utils/typings/student';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  constructor(private service: StudentService) { }

  studentsList: Student[] = []

  ngOnInit(): void {
    this.service.findAll().subscribe(data => {
      this.studentsList = data;
    });
  }

}
