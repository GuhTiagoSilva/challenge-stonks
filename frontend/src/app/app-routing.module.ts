import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursesFormComponent } from './components/courses/courses-form/courses-form.component';
import { CoursesListComponent } from './components/courses/courses-list/courses-list.component';
import { HomeComponent } from './components/home/home.component';
import { StudentsFormComponent } from './components/students/students-form/students-form.component';
import { StudentsListComponent } from './components/students/students-list/students-list.component';
import { TeachersFormComponent } from './components/teachers/teachers-form/teachers-form.component';
import { TeachersListComponent } from './components/teachers/teachers-list/teachers-list.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'courses/create', component: CoursesFormComponent},
  {path: 'courses/:id', component: CoursesFormComponent},
  {path: 'courses', component: CoursesListComponent},
  {path: 'teachers/create', component: TeachersFormComponent},
  {path: 'teachers/:id', component: TeachersFormComponent},
  {path: 'teachers', component: TeachersListComponent},
  {path: 'students', component: StudentsListComponent},
  {path: 'students/create', component: StudentsFormComponent},
  {path: 'students/:id', component: StudentsFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
