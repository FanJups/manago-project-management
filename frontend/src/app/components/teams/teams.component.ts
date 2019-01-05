import { Component, OnInit } from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Team} from '../../models/team';
import {TeamService} from '../../services/team.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {
  teams: MatTableDataSource<Team> = new MatTableDataSource();
  displayedColumns = ['name', 'size', 'monthlyCost', 'detail', 'edit', 'delete'];

  constructor(
    private teamService: TeamService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getTeams();
  }

  getTeams(): void {
    this.teamService.getTeams().subscribe(resp => {
      this.teams.data = resp;
    });
  }

}
