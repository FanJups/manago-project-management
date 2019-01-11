import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {History} from '../models/history';

@Injectable()
export class HistoryService {
  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }

  getHistory(taskId: string): Observable<History> {
    return this.httpClient.get<History>(this.appEndpoints.go().history(taskId), {observe: 'body'});
  }
}
