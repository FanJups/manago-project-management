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


@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    ProjectsComponent,
    TeamsComponent,
    CustomersComponent,
    ProjectComponent,
    ProjectEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  entryComponents: [ProjectEditComponent],
  providers: [AppEndpoints, CustomerService, ProjectService, TeamService],
  bootstrap: [AppComponent]
})
export class AppModule { }
