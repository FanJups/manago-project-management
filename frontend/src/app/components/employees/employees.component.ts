import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog, MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  constructor(
    private customerService: CustomerService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
  }

}
