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

  getTeam(name: string): Observable<Team> {
    return this.httpClient.get<Team>(this.appEndpoints.go().team(name), {observe: 'body'});
  }

  createTeam(teamData: any): Observable<Team> {
    return this.httpClient.post<Team>(this.appEndpoints.go().teams(), teamData, {responseType: 'json'});
  }

  updateTeam(teamData: any, name: string): Observable<Team> {
    return this.httpClient.put<Team>(this.appEndpoints.go().team(name), teamData, {responseType: 'json'});
  }

  deleteTeam(name: string): Observable<any> {
    return this.httpClient.delete(this.appEndpoints.go().team(name));
  }
}
