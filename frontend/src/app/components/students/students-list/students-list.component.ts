import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { StudentService } from 'src/app/services/students/student.service';
import { Student } from 'src/app/utils/typings/student';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  constructor(
    private service: StudentService,
    private route: Router,
    private toaster: ToastrService
  ) { }

  studentsList: Student[] = []

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe(data => {
      this.studentsList = data;
    });
  }

  updateStudent(id: number) {
    this.route.navigate(['students', id]);
  }

  deleteStudent(id: number) {
    this.service.delete(id).subscribe(data => {
      this.toaster.success('Registro excluído com sucesso');
      this.service.findAll().subscribe(data => {
        this.studentsList = data;
      });

    }, error => {
      this.toaster.error('Ocorreu um erro ao deletar o registro selecionado');
    });
  }

  createStudent() {
    this.route.navigate(['students/create']);
  }

}
