import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {EmploymentType} from '../../../models/employmentType';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {
  employmentTypeForm = new FormControl('valid', [
    Validators.required
  ]);
  empTypes = [EmploymentType.CONTRACT, EmploymentType.INTERNSHIP, EmploymentType.PERMANENT];
  constructor(public dialogRef: MatDialogRef<EmployeeEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isInvalid(): boolean {
    return this.employmentTypeForm.hasError('required');
  }

  changedEmploymentType(event: any): void {
    if(event.isUserInput) {
      this.data.employmentType = event.source.value;
    }
  }

  getHeader(): string {
    return this.data.edit ? "Edit " + this.data.lastName : "New Employee";
  }

}
