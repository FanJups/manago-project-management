import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-customer-invoice-data',
  templateUrl: './customer-invoice-data.component.html',
  styleUrls: []
})
export class CustomerInvoiceDataComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<CustomerInvoiceDataComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
