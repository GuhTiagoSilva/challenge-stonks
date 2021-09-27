import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Course } from 'src/app/utils/typings/course';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private httpClient: HttpClient) { }

  private apiUrl = environment.apiUrl;

  findAll() {
    return this.httpClient.get<Course[]>(`${this.apiUrl}/courses`);
  }

  create(course: Course) {
    return this.httpClient.post<Course>(`${this.apiUrl}/courses`, course);
  }

  findById(id: number) {
    return this.httpClient.get<Course>(`${this.apiUrl}/${id}`);
  }

}
