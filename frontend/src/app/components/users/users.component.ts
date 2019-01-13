import { Component, OnInit } from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {User} from '../../models/user';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {UserEditComponent} from './user-edit/user-edit.component';
import {EmploymentType} from '../../models/employmentType';
import {Employee} from '../../models/employee';
import {EmployeeService} from '../../services/employee.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  availableEmployees: Employee[];
  users: MatTableDataSource<User> = new MatTableDataSource();
  displayedColumns = ['username', 'email', 'employeeId', 'edit', 'detail', 'delete'];
  constructor(
    private userService: UserService,
    private employeeService: EmployeeService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getUsers();
    this.getEmployees();
  }

  getUsers(): void {
    this.userService.getUsers().subscribe(resp => {
      this.users.data = resp;
      console.log(resp);
    });
  }

  getEmployees(): void {
    this.employeeService.getEmployees().subscribe(resp => {
      this.availableEmployees = resp;
      console.log(resp);
    });
  }

  deleteUser(user: User): void {
    this.userService.deleteUser(user.username)
      .subscribe(resp => {
        this.snackbar.open('Successfully removed user', '', {
          duration: 2500
        });
      }, err => {
        console.log(err)
        this.snackbar.open(err, '', {
          duration: 10000
        });
      }, () => {
        this.getUsers();
      });
  }

  showUser(user: User): void {
    this.router.navigate(['users', user.username]);
  }

  createUserDialog(): void {
    const dialogRef = this.dialog.open(UserEditComponent, {
      width: '350px',
      data: { edit: false,
        username: '',
        email: '',
        employee: '',
        availableEmployees: this.availableEmployees
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if ((result.employee && result.employee.employeeId)) {
        let drop = false;
        this.users.data.forEach(u => {
          if (u.employeeResponse && u.employeeResponse.employeeId === result.employee.employeeId) {
            this.snackbar.open("This employee is already connected with another user!", '', {
              duration: 10000
            });
            drop = true;
            return;
          }
        });
        if (drop) return;
      }
      this.userService.createUser(
        {
          username: result.username,
          email: result.email,
          employeeId: result.employee.employeeId
        }
      ).subscribe(resp => {
        this.snackbar.open('Successfully created new user', '', {
          duration: 2500
        });
      }, err => {
        console.log(err)
        this.snackbar.open(err, '', {
          duration: 10000
        });
      }, () => {
        this.getUsers();
      });
    });
  }

  editUserDialog(user: User): void {
    const dialogRef = this.dialog.open(UserEditComponent, {
      width: '350px',
      data: {
        edit: true,
        username: user.username,
        email: user.email,
        employee: user.employeeResponse,
        availableEmployees: this.availableEmployees
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (!user.employeeResponse || (result.employee && result.employee.employeeId !== user.employeeResponse.employeeId)) {
        let drop = false;
        this.users.data.forEach(u => {
          if (u.employeeResponse && u.employeeResponse.employeeId === result.employee.employeeId) {
            this.snackbar.open("This employee is already connected with another user!", '', {
              duration: 10000
            });
            drop = true;
            return;
          }
        });
        if (drop) return;
      }
      this.userService.updateUser({
        username: result.username,
        email: result.email,
        employeeId: result.employee ? result.employee.employeeId : null
      }, user.username)
        .subscribe(resp => {
          this.snackbar.open('Successfully updated user', '', {
            duration: 2500
          });
        }, err => {
          console.log(err);
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
          this.getUsers();
        });
    });
  }

  displayEmployee(user: User): string {
    return user.employeeResponse ? user.employeeResponse.firstName + " " + user.employeeResponse.lastName : ""
  }

}
