import { Component, OnInit } from '@angular/core';
import {Team} from '../../../models/team';
import {ActivatedRoute, Router} from '@angular/router';
import {TeamService} from '../../../services/team.service';
import {Resource} from '../../../models/resource';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {
  team: Team = new Team();
  resourceDisplayedColumns = ['resourceId', 'name', 'resourceType', 'manufacturer', 'cost', 'boughtAt'];
  employeeDisplayedColumns = ['firstName', 'lastName', 'employmentType', 'salary'];
  constructor(
    private route: ActivatedRoute,
    private teamService: TeamService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getTeam(this.route.params['value'].teamName);
  }

  getTeam(teamName: string): void {
    this.teamService.getTeam(teamName).subscribe(resp => {
      this.team = resp;
    });
  }

  getDate(date: string): string {
    return new Date(date).toDateString();
  }


}
