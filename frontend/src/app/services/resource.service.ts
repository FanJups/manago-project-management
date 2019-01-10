import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from './app-endpoints.service';
import {Observable} from 'rxjs/Observable';
import {Resource} from '../models/resource';

@Injectable()
export class ResourceService {
  constructor(
    private httpClient: HttpClient,
    private appEndpoints: AppEndpoints
  ) {
  }

  getResources(): Observable<Resource[]> {
    return this.httpClient.get<Resource[]>(this.appEndpoints.go().resources(), {observe: 'body'});
  }

  getResource(resourceId: number): Observable<Resource> {
    return this.httpClient.get<Resource>(this.appEndpoints.go().resource(resourceId), {observe: 'body'});
  }

  createResource(resourceData: any): Observable<Resource> {
    return this.httpClient.post<Resource>(this.appEndpoints.go().resources(), resourceData, {responseType: 'json'});
  }

  updateResource(resourceData: any, resourceId: number): Observable<Resource> {
    return this.httpClient.put<Resource>(this.appEndpoints.go().resource(resourceId), resourceData, {responseType: 'json'});
  }

  deleteResource(resourceId: number): Observable<any> {
    return this.httpClient.delete(this.appEndpoints.go().resource(resourceId));
  }
}
