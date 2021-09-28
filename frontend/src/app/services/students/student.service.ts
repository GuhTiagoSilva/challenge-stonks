import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Student } from 'src/app/utils/typings/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private httpClient: HttpClient) { }

  private apiUrl = environment.apiUrl;

  create(student: Student) {
    return this.httpClient.post<Student>(`${this.apiUrl}/students`, student);
  }

  findById(id: number) {
    return this.httpClient.get<Student>(`${this.apiUrl}/students/${id}`);
  }

  findAll() {
    return this.httpClient.get<Student[]>(`${this.apiUrl}/students`);
  }

  update(id: number, student: Student) {
    return this.httpClient.put<Student>(`${this.apiUrl}/students/${id}`, student);
  }

  delete(id: number) {
    return this.httpClient.delete<void>(`${this.apiUrl}/students/${id}`);
  }

}
