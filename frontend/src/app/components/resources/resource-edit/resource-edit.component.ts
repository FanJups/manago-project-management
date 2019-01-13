import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDatepicker, MatDialogRef} from '@angular/material';
import {ResourceType} from '../../../models/resourceType';

@Component({
  selector: 'app-resource-edit',
  templateUrl: './resource-edit.component.html',
  styleUrls: ['./resource-edit.component.css']
})
export class ResourceEditComponent implements OnInit {
  resourceTypeForm = new FormControl(
    'valid', [
      Validators.required
    ]
  );
  picker: MatDatepicker<Date>;
  resourceType: ResourceType;
  serializedDate = new FormControl((new Date()).toISOString());
  constructor(public dialogRef: MatDialogRef<ResourceEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.data.resourceType = null;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isInvalid(): boolean {
    return !this.data.resourceType || this.data.name.length < 1;
  }

  changedResourceType(event: any): void {
    if (event.isUserInput) {
      this.data.resourceType = event.source.value;
    }
  }

  onDateChange(event: any): void {
    this.data.boughtAt = event.value.toISOString();
    console.log(this.data.boughtAt);
  }

}
