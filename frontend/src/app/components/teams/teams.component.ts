import { Component, OnInit } from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Team} from '../../models/team';
import {TeamService} from '../../services/team.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TeamEditComponent} from './team-edit/team-edit.component';
import {Resource} from '../../models/resource';
import {Employee} from '../../models/employee';
import {EmployeeService} from '../../services/employee.service';
import {ResourceService} from '../../services/resource.service';
import {DatabaseService} from '../../services/database.service';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {
  teams: MatTableDataSource<Team> = new MatTableDataSource();
  availableResources: Resource[];
  availableEmployees: Employee[];
  displayedColumns = ['name', 'size', 'monthlyCost', 'detail', 'edit', 'delete'];

  constructor(
    private teamService: TeamService,
    private employeeService: EmployeeService,
    private resourceService: ResourceService,
    private router: Router,
    public dialog: MatDialog,
    private route: ActivatedRoute,
    private snackbar: MatSnackBar,
    private databaseService: DatabaseService
  ) { }

  ngOnInit() {
    this.getTeams();
  }

  getTeams(): void {
    this.teamService.getTeams().subscribe(resp => {
      this.teams.data = resp;
    });
    this.getAdditionals();
  }

  getAdditionals(): void {
    this.employeeService.getEmployees().subscribe(resp => {
      this.availableEmployees = resp;
    });
    this.resourceService.getResources().subscribe(resp => {
      this.availableResources = resp;
    });
  }

  showTeam(team: Team): void {
    this.router.navigate(['teams', team.name]);
  }

  editTeamDialog(team: Team): void {
    const dialogRef = this.dialog.open(TeamEditComponent, {
      width: '350px',
      data: { edit: true,
        name: team.name,
        employeeIds: [],
        resourceIds: [],
        availableResources: this.availableResources.slice(),
        availableEmployees: this.availableEmployees.slice(),
        size: team.size,
        monthlyCost: team.monthlyCost}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.teamService.updateTeam(
        {
          name: team.name,
          employeeIds: result.employeeIds,
          resourceIds: result.resourceIds,
          size: team.size,
          monthlyCost: team.monthlyCost
        }, team.name)
        .subscribe(resp => {
          this.snackbar.open('Successfully updated team', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open(err.error.errorMessage, '', {
            duration: 10000
          });
        }, () => {
          this.getTeams();
        });
    });
  }

  createTeamDialog(): void {
    const dialogRef = this.dialog.open(TeamEditComponent, {
      width: '350px',
      data: {
        edit: false,
        name: '',
        employeeIds: [],
        resourceIds: [],
        availableResources: this.availableResources.slice(),
        availableEmployees: this.availableEmployees.slice(),
        size: 0,
        monthlyCost: 0
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.teamService.createTeam({
        name: result.name,
        employeeIds: result.employeeIds,
        resourceIds: result.resourceIds,
        size: 0,
        monthlyCost: 0
      })
        .subscribe(resp => {
          this.snackbar.open('Successfully created new team', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open(err.error.errorMessage, '', {
            duration: 10000
          });
        }, () => {
          this.getTeams();
        });
    });
  }

  deleteTeam(team: Team): void {
    this.teamService.deleteTeam(team.name).subscribe(resp => {
      this.snackbar.open('Successfully removed team', '', {
        duration: 2500
      });
    }, err => {
      console.log(err)
      this.snackbar.open("You can not delete a team which has resources or project assigned", '', {
        duration: 10000
      });
    }, () => {
      this.getTeams();
    });
  }

  onCalculate(): void {
    this.databaseService.procedure().subscribe(resp => {
      this.getTeams();
      this.getAdditionals();
    });
  }

}
