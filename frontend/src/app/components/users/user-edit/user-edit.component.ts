import {Component, Inject, OnInit} from '@angular/core';
import {Employee} from '../../../models/employee';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  employeeModel: Employee;
  constructor(public dialogRef: MatDialogRef<UserEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isInvalid(): boolean {
    return false;
  }

  changedEmployee(event: any): void {
    if(event.isUserInput) {
      this.data.employeeId = event.source.value.employeeId;
    }
  }

}
