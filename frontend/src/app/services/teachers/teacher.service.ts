import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Teacher } from 'src/app/utils/typings/teacher';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  constructor(
    private httpClient: HttpClient
  ) { }

  private apiUrl = environment.apiUrl;

  create(teacher: Teacher) {
    return this.httpClient.post<Teacher>(`${this.apiUrl}/teachers`, teacher);
  }

  findById(id: number) {
    return this.httpClient.get<Teacher>(`${this.apiUrl}/teachers/${id}`);
  }

  findAll() {
    return this.httpClient.get<Teacher[]>(`${this.apiUrl}/teachers`);
  }

  update(id: number, teacher: Teacher) {
    return this.httpClient.put<Teacher>(`${this.apiUrl}/teachers/${id}`, teacher);
  }

  delete(id: number) {
    return this.httpClient.delete<void>(`${this.apiUrl}/teachers/${id}`);
  }


}
