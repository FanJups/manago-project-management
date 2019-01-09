import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../../services/project.service';
import {Router} from '@angular/router';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Project} from '../../models/project';
import {ProjectEditComponent} from './project-edit/project-edit.component';
import {Customer} from '../../models/customer';
import {Team} from '../../models/team';
import {CustomerService} from '../../services/customer.service';
import {TeamService} from '../../services/team.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {
  projects: MatTableDataSource<Project> = new MatTableDataSource();
  customers: MatTableDataSource<Customer> = new MatTableDataSource();
  teams: MatTableDataSource<Team> = new MatTableDataSource();
  displayedColumns = ['name', 'description', 'customers', 'teamName', 'edit', 'detail', 'delete'];

  constructor(
    private projectService: ProjectService,
    private customerService: CustomerService,
    private teamService: TeamService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getProjects();
    this.getCustomers();
    this.getTeams();
  }

  getProjects(): void {
    this.projectService.getProjects().subscribe( resp => {
        this.projects.data = resp;
      }
    );
    console.log(this.projects.data);
  }

  getCustomers(): void {
    this.customerService.getCustomers().subscribe(resp => {
      this.customers.data = resp;
    });
  }

  getTeams(): void {
    this.teamService.getTeams().subscribe(resp => {
      this.teams.data = resp;
    });
  }

  createProjectDialog(): void {
    const dialogRef = this.dialog.open(ProjectEditComponent, {
      width: '350px',
      data: { edit: false, name: '', description: '', customers: this.customers.data.slice(), teams: this.teams.data.slice(), customerIds: null, teamName: null}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.projectService.createProject({name: result.name, description: result.description, customerIds: result.customerIds, teamName: result.teamName})
        .subscribe(resp => {
          this.snackbar.open('Successfully created new project', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open('Could not create new project', '', {
            duration: 2500
          });
        }, () => {
          this.getProjects();
        });
    });
  }

  editProjectDialog(project: Project): void {
    const dialogRef = this.dialog.open(ProjectEditComponent, {
      width: '350px',
      data: { edit: true, name: project.name, description: project.description, customers: this.customers.data.slice(), teams: this.teams.data.slice(), customerIds: project.customerResponses, teamName: project.teamResponse.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.projectService.updateProject(
        {description: result.description, customerIds: result.customerIds, teamName: result.teamName},
        result.name)
        .subscribe(resp => {
          this.snackbar.open('Successfully updated project', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open('Could not update project', '', {
            duration: 2500
          });
        }, () => {
          this.getProjects();
        });
    });
  }

  deleteProject(project: Project): void {
    this.projectService.deleteProject(project.name)
      .subscribe(resp => {
        this.snackbar.open('Successfully removed project', '', {
          duration: 2500
        });
      }, err => {
        console.log(err)
        this.snackbar.open('Could not remove project', '', {
          duration: 2500
        });
      }, () => {
        this.getProjects();
      });
  }

  showProject(project: Project): void {
    this.router.navigate(['projects', project.name]);
  }
}
