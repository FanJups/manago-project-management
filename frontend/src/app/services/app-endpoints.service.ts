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
    customer: (customerId) => `${this.host}/customers/${customerId}`,
    resources: () => `${this.host}/resources`,
    resource: (resourceId) => `${this.host}/resources/${resourceId}`,
    resourceTypes: () => `${this.host}/resources/types`,
    employees: () => `${this.host}/employees`,
    employee: (employeeId) => `${this.host}/employees/${employeeId}`,
    users: () => `${this.host}/users`,
    user: (username) => `${this.host}/users/${username}`,
    teams: () => `${this.host}/teams`,
    team: (name) => `${this.host}/teams/${name}`,
    tasks: (projectName) => `${this.host}/projects/${projectName}/tasks`,
    task: (projectName, taskId) => `${this.host}/projects/${projectName}/tasks/${taskId}`,
    history: (taskId) => `${this.host}/history/${taskId}`,
    statuses: () => `${this.host}/statuses`,
    status: (statusName) => `${this.host}/statuses/${statusName}`,
    function: (customerId) => `${this.host}/customers/${customerId}/function`,
    procedure: (name) => `${this.host}/teams/procedure/${name}`
  };
  go() {
    return this.paths;
  }
}
