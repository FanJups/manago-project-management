import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';

@Injectable()
export class TeamService {

  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }
}
