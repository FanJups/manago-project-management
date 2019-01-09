import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-project-edit',
  templateUrl: './project-edit.component.html',
  styleUrls: []
})
export class ProjectEditComponent implements OnInit {

  customersForm = new FormControl();
  currentIds = [];
  constructor(public dialogRef: MatDialogRef<ProjectEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.currentIds = [];
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isInvalid(): boolean {
    return false;
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

}
