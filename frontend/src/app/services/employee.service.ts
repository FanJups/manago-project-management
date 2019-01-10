import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../models/customer';

@Injectable()
export class EmployeeService {
  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }

  getCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(this.appEndpoints.go().customers(), {observe: 'body'});
  }

  getCustomer(customerId: number): Observable<Customer> {
    return this.httpClient.get<Customer>(this.appEndpoints.go().customer(customerId), {observe: 'body'});
  }

  createCustomer(customerData: any): Observable<Customer> {
    return this.httpClient.post<Customer>(this.appEndpoints.go().customers(), customerData, {responseType: 'json'});
  }

  updateCustomer(customerData: any, customerId: number): Observable<Customer> {
    return this.httpClient.put<Customer>(this.appEndpoints.go().customer(customerId), customerData, {responseType: 'json'});
  }

  deleteCustomer(customerId: number): Observable<any> {
    return this.httpClient.delete(this.appEndpoints.go().customer(customerId));
  }
}
