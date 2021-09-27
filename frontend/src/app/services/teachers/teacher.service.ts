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

  apiUrl = environment.apiUrl;

  create(teacher: Teacher) {
    return this.httpClient.post(`${this.apiUrl}/teachers`, teacher);
  }

  findById(id: number) {
    return this.httpClient.get(`${this.apiUrl}/${id}`);
  }

}
