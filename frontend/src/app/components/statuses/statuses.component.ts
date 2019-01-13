import { Component, OnInit } from '@angular/core';
import {StatusService} from '../../services/status.service';
import {Router} from '@angular/router';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Status} from '../../models/status';

@Component({
  selector: 'app-statuses',
  templateUrl: './statuses.component.html',
  styleUrls: ['./statuses.component.css']
})
export class StatusesComponent implements OnInit {
  statuses: MatTableDataSource<Status> = new MatTableDataSource();
  displayedColumns = ['name', 'delete'];
  newStatus: string = '';
  constructor(
    private statusService: StatusService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getStatuses();
  }

  getStatuses(): void {
    this.statusService.getStatuses().subscribe(resp => {
      console.log(resp);
      this.statuses.data = resp;
    });
  }

  createStatus(): void {
    if (this.newStatus.length < 1) {
      this.snackbar.open('Status must have a name', '', {
        duration: 10000
      });
      return;
    }
    this.statusService.createStatus({
      name: this.newStatus
    }).subscribe(resp => {
      this.snackbar.open('Successfully created new status', '', {
        duration: 2500
      });
    }, err => {
      console.log(err);
      this.snackbar.open('Could not create new status', '', {
        duration: 10000
      });
    }, () => {
      this.getStatuses();
      this.newStatus = '';
    });

  }

  deleteStatus(status: Status) {
    this.statusService.deleteStatus(status.name)
      .subscribe(resp => {
        this.snackbar.open('Successfully removed status', '', {
          duration: 2500
        });
      }, err => {
        console.log(err);
        this.snackbar.open(err.error.errorMessage, '', {
          duration: 10000
        });
      }, () => {
        this.getStatuses();
      });
  }

}
