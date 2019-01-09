import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable()
export class AppEndpoints {
  host = environment.host;
  paths = {
    root: () => `/`,
    project: (projectName) => `${this.host}/projects/${projectName}`,
    projects: () => `${this.host}/projects`,
    customers: () => `${this.host}/customers`,
    teams: () => `${this.host}/teams`,
  };
  go() {
    return this.paths;
  }
}
