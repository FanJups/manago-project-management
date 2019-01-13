import { Component, OnInit } from '@angular/core';
import {Resource} from '../../../models/resource';
import {ActivatedRoute, Router} from '@angular/router';
import {ResourceService} from '../../../services/resource.service';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {

  resource: Resource = new Resource();
  constructor(
    private route: ActivatedRoute,
    private resourceService: ResourceService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getResource(this.route.params['value'].resourceId);
  }

  getResource(resourceId: number): void {
    this.resourceService.getResource(resourceId).subscribe(resp => {
      this.resource = resp;
    });
  }

  getDate(date: string): string {
    return new Date(date).toDateString();
  }

}
