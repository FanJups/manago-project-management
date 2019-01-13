import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from './modules/material.module';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ProjectsComponent } from './components/projects/projects.component';
import { TeamsComponent } from './components/teams/teams.component';
import { CustomersComponent } from './components/customers/customers.component';
import { ProjectComponent } from './components/projects/project/project.component';
import {AppRoutingModule} from './modules/routing.module';
import {AppEndpoints} from './services/app-endpoints.service';
import {CustomerService} from './services/customer.service';
import {ProjectService} from './services/project.service';
import {TeamService} from './services/team.service';
import { ProjectEditComponent } from './components/projects/project-edit/project-edit.component';
import { CustomerEditComponent } from './components/customers/customer-edit/customer-edit.component';
import { CustomerComponent } from './components/customers/customer/customer.component';
import { TeamComponent } from './components/teams/team/team.component';
import { TeamEditComponent } from './components/teams/team-edit/team-edit.component';
import { TasksComponent } from './components/tasks/tasks.component';
import { TaskComponent } from './components/tasks/task/task.component';
import { TaskEditComponent } from './components/tasks/task-edit/task-edit.component';
import {TaskService} from './services/task.service';
import { EmployeesComponent } from './components/employees/employees.component';
import { UsersComponent } from './components/users/users.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { EmployeeComponent } from './components/employees/employee/employee.component';
import { UserComponent } from './components/users/user/user.component';
import { ResourceComponent } from './components/resources/resource/resource.component';
import {EmployeeService} from './services/employee.service';
import {ResourceService} from './services/resource.service';
import {UserService} from './services/user.service';
import { EmployeeEditComponent } from './components/employees/employee-edit/employee-edit.component';
import { ResourceEditComponent } from './components/resources/resource-edit/resource-edit.component';
import { UserEditComponent } from './components/users/user-edit/user-edit.component';
import { StatusesComponent } from './components/statuses/statuses.component';
import { HistoryComponent } from './components/history/history.component';
import {HistoryService} from './services/history.service';
import {StatusService} from './services/status.service';


@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    ProjectsComponent,
    TeamsComponent,
    CustomersComponent,
    ProjectComponent,
    ProjectEditComponent,
    CustomerEditComponent,
    CustomerComponent,
    TeamComponent,
    TeamEditComponent,
    TasksComponent,
    TaskComponent,
    TaskEditComponent,
    EmployeesComponent,
    UsersComponent,
    ResourcesComponent,
    EmployeeComponent,
    UserComponent,
    ResourceComponent,
    EmployeeEditComponent,
    ResourceEditComponent,
    UserEditComponent,
    StatusesComponent,
    HistoryComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  entryComponents: [UserEditComponent, HistoryComponent, ProjectEditComponent, CustomerEditComponent, TaskEditComponent, EmployeeEditComponent, TeamEditComponent],
  providers: [AppEndpoints, StatusService, CustomerService, ProjectService, TeamService, TaskService, EmployeeService, ResourceService, UserService, HistoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
