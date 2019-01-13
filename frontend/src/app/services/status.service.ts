import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {Status} from '../models/status';

@Injectable()
export class StatusService {
  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }

  getStatuses(): Observable<Status[]> {
    return this.httpClient.get<Status[]>(this.appEndpoints.go().statuses(), {observe: 'body'});
  }

  getStatus(statusId: string): Observable<Status> {
    return this.httpClient.get<Status>(this.appEndpoints.go().status(statusId), {observe: 'body'});
  }

  createStatus(statusData: any): Observable<Status> {
    return this.httpClient.post<Status>(this.appEndpoints.go().statuses(), statusData, {responseType: 'json'});
  }

  updateStatus(statusData: any, statusId: string): Observable<Status> {
    return this.httpClient.put<Status>(this.appEndpoints.go().status(statusId), statusData, {responseType: 'json'});
  }

  deleteStatus(statusId: string): Observable<any> {
    return this.httpClient.delete(this.appEndpoints.go().status(statusId));
  }
}
