import {Component, OnInit} from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Customer} from '../../models/customer';
import {Router} from '@angular/router';
import {CustomerService} from '../../services/customer.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers: MatTableDataSource<Customer> = new MatTableDataSource();
  displayedColumns = ['firstName', 'lastName', 'email', 'company', 'address', 'zipCode', 'city', 'edit', 'detail', 'delete'];

  constructor(
    private customerService: CustomerService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.getCustomers().subscribe(resp => {
      this.customers.data = resp;
      this.customers.data = this.customers.data.sort((a, b) => {
        if (a.lastName > b.lastName) return 1;
        if (a.lastName === b.lastName) return 0;
        return -1;
      })
    });
  }

}
