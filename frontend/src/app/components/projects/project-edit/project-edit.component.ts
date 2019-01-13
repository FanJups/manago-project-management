import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormControl, Validators} from '@angular/forms';
import {Team} from '../../../models/team';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: []
})
export class ProjectEditComponent implements OnInit {

  customersForm = new FormControl('valid', [
  ]);
  teamsForm = new FormControl('valid', [
    Validators.required
  ])
  currentIds = [];
  teamModel: Team;
  constructor(public dialogRef: MatDialogRef<ProjectEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.data.customers = this.data.customers.sort((a,b) => {
      if (a.lastName > b.lastName) return 1;
      if (a.lastName === b.lastName) return 0;
      return -1;
    });
    this.currentIds = [];
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isInvalid(): boolean {
    return this.data.name.length < 1;
  }

  changedCustomer(event: any): void {
    if (event.isUserInput) {
      const currId = event.source.value.customerId;
      console.log(currId);
      if (this.currentIds.indexOf(currId) > -1) {
        this.currentIds.splice(this.currentIds.indexOf(currId), 1);
      } else {
        this.currentIds.push(currId);
      }
    }
    this.data.customerIds = this.currentIds.slice();
  }

  changedTeam(event: any): void {
    if(event.isUserInput) {
      this.data.teamName = event.source.value.name;
    }
  }

  getHeader(): string {
    return this.data.edit ? "Edit " + this.data.name : "New Project";
  }

}
