import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursesFormComponent } from './components/courses/courses-form/courses-form.component';
import { CoursesListComponent } from './components/courses/courses-list/courses-list.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { StudentsFormComponent } from './components/students/students-form/students-form.component';
import { StudentsListComponent } from './components/students/students-list/students-list.component';
import { TeachersFormComponent } from './components/teachers/teachers-form/teachers-form.component';
import { TeachersListComponent } from './components/teachers/teachers-list/teachers-list.component';
import { AuthGuardService } from './services/guards/auth-guard.service';


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
  {path: 'courses/create', component: CoursesFormComponent, canActivate: [AuthGuardService]},
  {path: 'courses/:id', component: CoursesFormComponent, canActivate: [AuthGuardService]},
  {path: 'courses', component: CoursesListComponent, canActivate: [AuthGuardService]},
  {path: 'teachers/create', component: TeachersFormComponent, canActivate: [AuthGuardService]},
  {path: 'teachers/:id', component: TeachersFormComponent, canActivate: [AuthGuardService]},
  {path: 'teachers', component: TeachersListComponent, canActivate: [AuthGuardService]},
  {path: 'students', component: StudentsListComponent, canActivate: [AuthGuardService]},
  {path: 'students/create', component: StudentsFormComponent, canActivate: [AuthGuardService]},
  {path: 'students/:id', component: StudentsFormComponent, canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
