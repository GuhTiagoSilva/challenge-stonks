import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursesFormComponent } from './components/courses-form/courses-form.component';
import { CoursesListComponent } from './components/courses-list/courses-list.component';
import { HomeComponent } from './components/home/home.component';
import { StudentsFormComponent } from './components/students-form/students-form.component';
import { StudentsListComponent } from './components/students-list/students-list.component';
import { TeachersFormComponent } from './components/teachers-form/teachers-form.component';
import { TeachersListComponent } from './components/teachers-list/teachers-list.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'courses/create', component: CoursesFormComponent},
  {path: 'courses', component: CoursesListComponent},
  {path: 'teachers/create', component: TeachersFormComponent},
  {path: 'teachers', component: TeachersListComponent},
  {path: 'students', component: StudentsListComponent},
  {path: 'students/create', component: StudentsFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
