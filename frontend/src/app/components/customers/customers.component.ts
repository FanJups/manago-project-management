import {Component, OnInit} from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Customer} from '../../models/customer';
import {Router} from '@angular/router';
import {CustomerService} from '../../services/customer.service';
import {ProjectEditComponent} from '../projects/project-edit/project-edit.component';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {CustomerInvoiceDataComponent} from './customer-invoice-data/customer-invoice-data.component';
import {DatabaseService} from '../../services/database.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers: MatTableDataSource<Customer> = new MatTableDataSource();
  displayedColumns = ['firstName', 'lastName', 'email', 'company', 'address', 'zipCode', 'city', 'data', 'edit', 'detail', 'delete'];

  constructor(
    private customerService: CustomerService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar,
    private databaseService: DatabaseService
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

  deleteCustomer(customer: Customer): void {
    this.customerService.deleteCustomer(customer.customerId)
      .subscribe(resp => {
        this.snackbar.open('Successfully removed customer', '', {
          duration: 2500
        });
      }, err => {
        console.log(err)
        this.snackbar.open(err, '', {
          duration: 10000
        });
      }, () => {
        this.getCustomers();
      });
  }

  showCustomer(customer: Customer): void {
    this.router.navigate(['customers', customer.customerId]);
  }

  createCustomerDialog(): void {
    const dialogRef = this.dialog.open(CustomerEditComponent, {
      width: '350px',
      data: { edit: false, firstName: '', lastName: '', email: '', company: '', address: '', zipCode: '', city: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.customerService.createCustomer({firstName: result.firstName, lastName: result.lastName, email: result.email, company: result.company, address: result.address, zipCode: result.zipCode, city: result.city})
        .subscribe(resp => {
          this.snackbar.open('Successfully created new customer', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
          this.getCustomers();
        });
    });
  }

  editCustomerDialog(customer: Customer): void {
    const dialogRef = this.dialog.open(CustomerEditComponent, {
      width: '350px',
      data: {
        edit: true,
        firstName: customer.firstName,
        lastName: customer.lastName,
        email: customer.email,
        company: customer.company,
        address: customer.address,
        zipCode: customer.zipCode,
        city: customer.city
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.customerService.updateCustomer({
        firstName: result.firstName,
        lastName: result.lastName,
        email: result.email,
        company: result.company,
        address: result.address,
        zipCode: result.zipCode,
        city: result.city
      }, customer.customerId)
        .subscribe(resp => {
          this.snackbar.open('Successfully updated customer', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
          this.getCustomers();
        });
    });
  }

  getData(customer: Customer): void {
    console.log(customer);
    this.databaseService.function(customer.customerId.toString()).subscribe(resp => {
      console.log(resp);
        const dialogRef = this.dialog.open(CustomerInvoiceDataComponent, {
          width: '650px',
          data: resp.body
        });
      }, err => {
        const dialogRef = this.dialog.open(CustomerInvoiceDataComponent, {
          width: '400px',
          data: {
            info: `Could not fetch data for ${customer.firstName} ${customer.lastName}`
          }
        });
    });
  }
}
