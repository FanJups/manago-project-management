import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {User} from '../models/user';

@Injectable()
export class UserService {
  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }

  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.appEndpoints.go().users(), {observe: 'body'});
  }

  getUser(userId: number): Observable<User> {
    return this.httpClient.get<User>(this.appEndpoints.go().user(userId), {observe: 'body'});
  }

  createUser(userData: any): Observable<User> {
    return this.httpClient.post<User>(this.appEndpoints.go().users(), userData, {responseType: 'json'});
  }

  updateUser(userData: any, userId: string): Observable<User> {
    return this.httpClient.put<User>(this.appEndpoints.go().user(userId), userData, {responseType: 'json'});
  }

  deleteUser(userId: string): Observable<any> {
    return this.httpClient.delete(this.appEndpoints.go().user(userId));
  }
}
