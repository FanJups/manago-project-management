import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../../services/project.service';
import {Project} from '../../../models/project';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
  project: Project = new Project();
  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getProject(this.route.params['value'].projectName);
  }

  getProject(projectName: string): void {
    this.projectService.getProject(projectName).subscribe(resp => {
      this.project = resp;
      console.log(this.project);
    });
  }

  goBack() {
    this.router.navigate(['projects']);
  }

}
