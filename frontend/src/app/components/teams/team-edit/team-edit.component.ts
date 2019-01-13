import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-team-edit',
  templateUrl: './team-edit.component.html',
  styleUrls: []
})
export class TeamEditComponent implements OnInit {
  resourcesForm = new FormControl('valid', [
    Validators.required
  ]);
  employeesForm = new FormControl('valid', [
    Validators.required
  ]);
  currentResourceIds = [];
  currentEmployeeIds = [];
  constructor(public dialogRef: MatDialogRef<TeamEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.currentEmployeeIds = [];
    this.currentEmployeeIds = [];
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isInvalid(): boolean {
    return (
      this.employeesForm.hasError('required') ||
        this.resourcesForm.hasError('required') ||
        this.data.name.length < 1
    );
  }

  changedEmployee(event: any): void {
    if (event.isUserInput) {
      const currId = event.source.value.employeeId;
      console.log(currId);
      if (this.currentEmployeeIds.indexOf(currId) > -1) {
        this.currentEmployeeIds.splice(this.currentEmployeeIds.indexOf(currId), 1);
      } else {
        this.currentEmployeeIds.push(currId);
      }
    }
    this.data.employeeIds = this.currentEmployeeIds.slice();
  }

  changedResource(event: any): void {
    if (event.isUserInput) {
      const currId = event.source.value.resourceId;
      console.log(currId);
      if (this.currentResourceIds.indexOf(currId) > -1) {
        this.currentResourceIds.splice(this.currentResourceIds.indexOf(currId), 1);
      } else {
        this.currentResourceIds.push(currId);
      }
    }
    this.data.resourceIds = this.currentResourceIds.slice();
  }

  getHeader(): string {
    return this.data.edit ? "Edit " + this.data.name : "New Team";
  }

}
