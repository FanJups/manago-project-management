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
}
