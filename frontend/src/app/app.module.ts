import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FooterComponent } from './components/footer/footer.component';
import { TeachersFormComponent } from './components/teachers/teachers-form/teachers-form.component';
import { TeachersListComponent } from './components/teachers/teachers-list/teachers-list.component';
import { StudentsListComponent } from './components/students/students-list/students-list.component';
import { StudentsFormComponent } from './components/students/students-form/students-form.component';
import { HomeComponent } from './components/home/home.component';
import { CoursesListComponent } from './components/courses/courses-list/courses-list.component';
import { MenuComponent } from './components/menu/menu.component';
import { CoursesFormComponent } from './components/courses/courses-form/courses-form.component';

@NgModule({
  declarations: [
    AppComponent,
    CoursesFormComponent,
    MenuComponent,
    CoursesListComponent,
    FooterComponent,
    TeachersFormComponent,
    TeachersListComponent,
    StudentsListComponent,
    StudentsFormComponent,
    HomeComponent
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
