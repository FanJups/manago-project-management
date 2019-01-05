import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable()
export class AppEndpoints {
  host = environment.host;
  paths = {
    root: () => `/`,
    projects: () => `${this.host}/projects`,
    customers: () => `${this.host}/customers`,
    teams: () => `${this.host}/teams`,
  };
  go() {
    return this.paths;
  }
}
