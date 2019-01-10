import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {Task} from '../models/task';

@Injectable()
export class TaskService {
  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ){}

  getTasks(projectName: string): Observable<Task[]> {
    return this.httpClient.get<Task[]>(this.appEndpoints.go().tasks(projectName), {observe: 'body'});
  }

  getTask(projectName: string, taskName: string): Observable<Task> {
    return this.httpClient.get<Task>(this.appEndpoints.go().task(projectName, taskName), {observe: 'body'});
  }

  createTask(taskData: any, projectName: string): Observable<Task> {
    return this.httpClient.post<Task>(this.appEndpoints.go().tasks(projectName), taskData, {responseType: 'json'});
  }

  updateTask(taskData: any, projectName: string, taskName: string): Observable<Task> {
    return this.httpClient.put<Task>(this.appEndpoints.go().task(projectName, taskName), taskData, {responseType: 'json'});
  }

  deleteTask(projectName: string, taskName: string): Observable<any> {
    return this.httpClient.delete(this.appEndpoints.go().task(projectName, taskName));
  }
}
