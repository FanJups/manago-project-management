import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {Project} from '../models/project';

@Injectable()
export class ProjectService {

  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }

  getProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.appEndpoints.go().projects(), {observe: 'body'});
  }

  getProject(projectName: string): Observable<Project> {
    return this.httpClient.get<Project>(this.appEndpoints.go().project(projectName), {observe: 'body'});
  }

  createProject(projectData: any): Observable<Project> {
    return this.httpClient.post<Project>(this.appEndpoints.go().projects(), projectData, {responseType: 'json'});
  }

  updateProject(projectData: any, projectName: string): Observable<Project> {
    return this.httpClient.put<Project>(this.appEndpoints.go().project(projectName), projectData, {responseType: 'json'});

  }
}
