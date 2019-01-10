import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from '../components/page-not-found/page-not-found.component';
import {CommonModule} from '@angular/common';
import {ProjectsComponent} from '../components/projects/projects.component';
import {ProjectComponent} from '../components/projects/project/project.component';
import {TeamsComponent} from '../components/teams/teams.component';
import {CustomersComponent} from '../components/customers/customers.component';
import {CustomerComponent} from '../components/customers/customer/customer.component';
import {TasksComponent} from '../components/tasks/tasks.component';
import {TaskComponent} from '../components/tasks/task/task.component';
import {EmployeesComponent} from '../components/employees/employees.component';
import {ResourcesComponent} from '../components/resources/resources.component';
import {UsersComponent} from '../components/users/users.component';
import {EmployeeComponent} from '../components/employees/employee/employee.component';
import {ResourceComponent} from '../components/resources/resource/resource.component';
import {UserComponent} from '../components/users/user/user.component';


const appRoutes: Routes = [
  {path: 'projects', component: ProjectsComponent},
  {path: 'projects/:projectName', component: ProjectComponent},
  {path: 'projects/:projectName/tasks', component: TasksComponent},
  {path: 'projects/:projectName/tasks/:taskId', component: TaskComponent},
  {path: 'teams', component: TeamsComponent},
  {path: 'customers', component: CustomersComponent},
  {path: 'employees', component: EmployeesComponent},
  {path: 'resources', component: ResourcesComponent},
  {path: 'users', component: UsersComponent},
  {path: 'customers/:customerId', component: CustomerComponent},
  {path: 'employees/:employeeId', component: EmployeeComponent},
  {path: 'resources/:resourceId', component: ResourceComponent},
  {path: 'users/:username', component: UserComponent},
  {path: '', redirectTo: '/projects', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(
      appRoutes
    ),
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
