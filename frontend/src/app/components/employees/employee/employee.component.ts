import { Component, OnInit } from '@angular/core';
import {Employee} from '../../../models/employee';
import {ActivatedRoute, Router} from '@angular/router';
import {EmployeeService} from '../../../services/employee.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employee: Employee = new Employee();
  constructor(
    private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getEmployee(this.route.params['value'].employeeId);
  }

  getEmployee(employeeId: number): void {
    this.employeeService.getEmployee(employeeId).subscribe(resp => {
      this.employee = resp;
    });
  }

}
