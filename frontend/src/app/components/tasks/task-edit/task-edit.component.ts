import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: []
})
export class TaskEditComponent implements OnInit {
  parentTaskForm = new FormControl('valid', [
    Validators.required
  ]);
  constructor(
    public dialogRef: MatDialogRef<TaskEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
  }

  getHeader(): string {
    return this.data.edit ? "Edit " + this.data.name : "New Task";
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isInvalid(): boolean {
    return false;
  }

}
