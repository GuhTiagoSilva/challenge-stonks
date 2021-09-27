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
    return this.httpClient.get<Teacher>(`${this.apiUrl}/${id}`);
  }

  findAll() {
    return this.httpClient.get<Teacher[]>(`${this.apiUrl}/teachers`);
  }

}
