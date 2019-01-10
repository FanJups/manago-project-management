import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {Employee} from '../models/employee';

@Injectable()
export class EmployeeService {
  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }

  getEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.appEndpoints.go().employees(), {observe: 'body'});
  }

  getEmployee(employeeId: number): Observable<Employee> {
    return this.httpClient.get<Employee>(this.appEndpoints.go().employee(employeeId), {observe: 'body'});
  }

  createEmployee(employeeData: any): Observable<Employee> {
    return this.httpClient.post<Employee>(this.appEndpoints.go().employees(), employeeData, {responseType: 'json'});
  }

  updateEmployee(employeeData: any, employeeId: number): Observable<Employee> {
    return this.httpClient.put<Employee>(this.appEndpoints.go().employee(employeeId), employeeData, {responseType: 'json'});
  }

  deleteEmployee(employeeId: number): Observable<any> {
    return this.httpClient.delete(this.appEndpoints.go().employee(employeeId));
  }
}
