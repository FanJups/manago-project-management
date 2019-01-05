import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {Team} from '../models/team';

@Injectable()
export class TeamService {

  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }

  getTeams(): Observable<Team[]> {
    return this.httpClient.get<Team[]>(this.appEndpoints.go().teams(), {observe: 'body'});
  }
}
