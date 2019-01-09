import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: []
})
export class CustomerEditComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<CustomerEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isInvalid(): boolean {
    return !(this.data['firstName'].length > 0 &&
      this.data['lastName'].length > 0 &&
      this.data['email'].length > 0);
  }

}
