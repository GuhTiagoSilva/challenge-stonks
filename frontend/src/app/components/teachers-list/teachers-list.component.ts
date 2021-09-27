import { Component, OnInit } from '@angular/core';
import { Teacher } from 'src/app/utils/typings/teacher';

@Component({
  selector: 'app-teachers-list',
  templateUrl: './teachers-list.component.html',
  styleUrls: ['./teachers-list.component.css']
})
export class TeachersListComponent implements OnInit {

  constructor() { }

  teacherList: Teacher[] = [
    {id: 1, bornDate: new Date(), email: 'gustavo@gmail.com', firstName: 'Gustavo', lastName: 'Silva', roleId: 1, yearsOfExperience: 3},
    {id: 2, bornDate: new Date(), email: 'anderson@gmail.com', firstName: 'Anderson', lastName: 'Carvalho', roleId: 2, yearsOfExperience: 4},
    {id: 3, bornDate: new Date(), email: 'paulo@gmail.com', firstName: 'Paulo', lastName: 'Silva', roleId: 3, yearsOfExperience: 5},
    {id: 4, bornDate: new Date(), email: 'tiago@gmail.com', firstName: 'Tiago', lastName: 'Souza', roleId: 1, yearsOfExperience: 5},
    {id: 5, bornDate: new Date(), email: 'gabriel@gmail.com', firstName: 'Gabriel', lastName: 'Henrique', roleId: 2, yearsOfExperience: 3}
  ]

  ngOnInit(): void {
  }

}
