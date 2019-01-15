import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class DatabaseService {
  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {}

  function(custId: string): Observable<any> {
    return this.httpClient.get(this.appEndpoints.go().function(custId), {observe: 'response'});
  }

  procedure(teamName: string): Observable<any> {
    return this.httpClient.get(this.appEndpoints.go().procedure(teamName), {observe: 'body'});
  }

}
