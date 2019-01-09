import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../../services/project.service';
import {CustomerService} from '../../../services/customer.service';
import {Customer} from '../../../models/customer';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customer: Customer = new Customer();
  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getCustomer(this.route.params['value'].customerId);
  }

  getCustomer(customerId: number): void {
    this.customerService.getCustomer(customerId).subscribe(resp => {
      this.customer = resp;
    });
  }
}
