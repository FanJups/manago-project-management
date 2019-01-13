import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {EmployeeService} from '../../services/employee.service';
import {Employee} from '../../models/employee';
import {EmployeeEditComponent} from './employee-edit/employee-edit.component';
import {EmploymentType} from '../../models/employmentType';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
  employees: MatTableDataSource<Employee> = new MatTableDataSource();
  displayedColumns = ['firstName', 'lastName', 'employmentType', 'salary', 'edit', 'detail', 'delete'];
  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getEmployees();
  }

  getEmployees(): void {
    this.employeeService.getEmployees().subscribe(resp => {
      this.employees.data = resp;
      console.log(resp);
    });
  }

  deleteEmployee(employee: Employee): void {
    this.employeeService.deleteEmployee(employee.employeeId)
      .subscribe(resp => {
        this.snackbar.open('Successfully removed employee', '', {
          duration: 2500
        });
      }, err => {
        console.log(err)
        this.snackbar.open(err, '', {
          duration: 10000
        });
      }, () => {
        this.getEmployees();
      });
  }

  showEmployee(employee: Employee): void {
    this.router.navigate(['employees', employee.employeeId]);
  }

  createEmployeeDialog(): void {
    const dialogRef = this.dialog.open(EmployeeEditComponent, {
      width: '350px',
      data: { edit: false, firstName: '', lastName: '', salary: 0, employmentType: EmploymentType.PERMANENT}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.employeeService.createEmployee(
        {
          firstName: result.firstName,
          lastName: result.lastName,
          salary: result.salary,
          employmentType: result.employmentType
        }
      ).subscribe(resp => {
          this.snackbar.open('Successfully created new employee', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
          this.getEmployees();
        });
    });
  }

  editEmployeeDialog(employee: Employee): void {
    const dialogRef = this.dialog.open(EmployeeEditComponent, {
      width: '350px',
      data: {
        edit: true,
        firstName: employee.firstName,
        lastName: employee.lastName,
        salary: employee.salary,
        employmentType: employee.employmentType
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.employeeService.updateEmployee({
        firstName: result.firstName,
        lastName: result.lastName,
        salary: result.salary,
        employmentType: result.employmentType
      }, employee.employeeId)
        .subscribe(resp => {
          this.snackbar.open('Successfully updated customer', '', {
            duration: 2500
          });
        }, err => {
          console.log(err);
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
          this.getEmployees();
        });
    });
  }

}
