import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/utils/typings/student';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  constructor() { }

  studentsList: Student[] = [
    {id: 1, bornDate: new Date(), email: 'gustavo@gmail.com', firstName: 'Gustavo', lastName: 'Silva', roleId: 1, registrationCode: 'X262525'},
    {id: 2, bornDate: new Date(), email: 'anderson@gmail.com', firstName: 'Anderson', lastName: 'Carvalho', roleId: 2, registrationCode: 'X212345'},
    {id: 3, bornDate: new Date(), email: 'paulo@gmail.com', firstName: 'Paulo', lastName: 'Silva', roleId: 3, registrationCode: 'X264234'},
    {id: 4, bornDate: new Date(), email: 'tiago@gmail.com', firstName: 'Tiago', lastName: 'Souza', roleId: 1, registrationCode: 'X263125'},
    {id: 5, bornDate: new Date(), email: 'gabriel@gmail.com', firstName: 'Gabriel', lastName: 'Henrique', roleId: 2, registrationCode: 'X362125'}
  ]

  ngOnInit(): void {
  }

}
