import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TeacherService } from 'src/app/services/teachers/teacher.service';
import { Teacher } from 'src/app/utils/typings/teacher';

@Component({
  selector: 'app-teachers-list',
  templateUrl: './teachers-list.component.html',
  styleUrls: ['./teachers-list.component.css']
})
export class TeachersListComponent implements OnInit {

  constructor(
    private service: TeacherService,
    private route: Router,
    private toaster: ToastrService
  ) { }

  teacherList: Teacher[] = [];

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe(data => {
      this.teacherList = data;
    });
  }

  updateTeacher(id: number) {
    this.route.navigate(['teachers', id])
  }

  createTeacher() {
    this.route.navigate(['teachers/create']);
  }

  deleteTeacher(id: number) {
    this.service.delete(id).subscribe(data => {
      this.toaster.success('Registro deletado com sucesso');
      this.service.findAll().subscribe(data => {
        this.teacherList = data;
        setTimeout(() => {
          this.route.navigate(['teachers']);
        }, 1000)
      });

    }, error => {
      this.toaster.error('Ocorreu um erro ao deletar o registro selecionado');
    });
  }

}
