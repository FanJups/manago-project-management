import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(
    private router: Router
  ) {}

  goBack() {
    this.router.navigate(['']);
  }

  goToCustomers() {
    this.router.navigate(['customers']);
  }

  goToTeams() {
    this.router.navigate(['teams']);
  }

  goToUsers() {
    this.router.navigate(['users']);
  }

  goToResources() {
    this.router.navigate(['resources']);
  }

  goToEmployees() {
    this.router.navigate(['employees']);
  }

  goToStatuses() {
    this.router.navigate(['statuses']);

  }
}
