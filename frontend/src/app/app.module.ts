import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {MaterialModule} from './modules/material.module';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ProjectsComponent } from './components/projects/projects.component';
import { TeamsComponent } from './components/teams/teams.component';
import { CustomersComponent } from './components/customers/customers.component';
import { ProjectComponent } from './components/projects/project/project.component';
import {AppRoutingModule} from './modules/routing.module';
import {AppEndpoints} from './services/app-endpoints.service';


@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    ProjectsComponent,
    TeamsComponent,
    CustomersComponent,
    ProjectComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MaterialModule
  ],
  providers: [AppEndpoints],
  bootstrap: [AppComponent]
})
export class AppModule { }
