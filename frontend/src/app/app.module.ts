import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
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
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatDatepickerModule } from '@angular/material/datepicker'
import { MatNativeDateModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { ToastyModule } from 'ng2-toasty';
import { ToastComponent } from './components/toast/toast.component';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './components/login/login.component';
import { HttpInterceptorBasicAuthService } from './services/http/http-interceptor-basic-auth.service';
import { LogoutComponent } from './components/logout/logout.component';

@NgModule({
  declarations: [
    ToastComponent,
    AppComponent,
    CoursesFormComponent,
    MenuComponent,
    CoursesListComponent,
    FooterComponent,
    TeachersFormComponent,
    TeachersListComponent,
    StudentsListComponent,
    StudentsFormComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    ToastrModule.forRoot(),
    MatIconModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    ToastyModule.forRoot()
  ],
  providers: [
    { provide: MatDatepickerModule },
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorBasicAuthService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
