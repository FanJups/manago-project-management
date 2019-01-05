import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../../services/project.service';
import {Router} from '@angular/router';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Project} from '../../models/project';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {
  projects: MatTableDataSource<Project> = new MatTableDataSource();
  displayedColumns = ['name', 'description', 'customers', 'teamName', 'edit', 'detail', 'delete'];

  constructor(
    private projectService: ProjectService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getProjects();
  }

  getProjects(): void {
    this.projectService.getProjects().subscribe( resp => {
        this.projects.data = resp;
      }
    );
    console.log(this.projects.data);
  }
}
