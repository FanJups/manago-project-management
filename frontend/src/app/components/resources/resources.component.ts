import { Component, OnInit } from '@angular/core';
import {ResourceService} from '../../services/resource.service';
import {Router} from '@angular/router';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Resource} from '../../models/resource';
import {ResourceEditComponent} from '../resources/resource-edit/resource-edit.component';
import {EmploymentType} from '../../models/employmentType';
import {ResourceType} from '../../models/resourceType';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit {
  resources: MatTableDataSource<Resource> = new MatTableDataSource();
  displayedColumns = [ 'name', 'resourceType', 'manufacturer', 'cost', 'boughtAt', 'edit', 'detail', 'delete'];
  constructor(
    private resourceService: ResourceService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getResources();
  }

  getResources(): void {
    this.resourceService.getResources().subscribe(resp => {
      this.resources.data = resp;
      console.log(resp);
    });
  }

  deleteResource(resource: Resource): void {
    this.resourceService.deleteResource(resource.resourceId)
      .subscribe(resp => {
        this.snackbar.open('Successfully removed resource', '', {
          duration: 2500
        });
      }, err => {
        console.log(err)
        this.snackbar.open(err, '', {
          duration: 10000
        });
      }, () => {
        this.getResources();
      });
  }

  showResource(resource: Resource): void {
    this.router.navigate(['resources', resource.resourceId]);
  }

  createResourceDialog(): void {
    const dialogRef = this.dialog.open(ResourceEditComponent, {
      width: '350px',
      data: { edit: false, name: '', resourceType: '', cost: 0, manufacturer: '', boughtAt: Date.now()}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.resourceService.createResource(
        {
          name: result.name,
          cost: result.cost,
          manufacturer: result.manufacturer,
          resourceType: result.resourceType,
          boughtAt: result.boughtAt
        }
      ).subscribe(resp => {
        this.snackbar.open('Successfully created new resource', '', {
          duration: 2500
        });
      }, err => {
        console.log(err)
        this.snackbar.open(err, '', {
          duration: 10000
        });
      }, () => {
        this.getResources();
      });
    });
  }

  editResourceDialog(resource: Resource): void {
    const dialogRef = this.dialog.open(ResourceEditComponent, {
      width: '350px',
      data: {
        edit: true,
        name: resource.name,
        resourceType: resource.resourceType,
        cost: resource.cost,
        manufacturer: resource.manufacturer,
        boughtAt: resource.boughtAt
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.resourceService.updateResource({
        name: result.name,
        cost: result.cost,
        manufacturer: result.manufacturer,
        resourceType: result.resourceType,
        boughtAt: result.boughtAt
      }, resource.resourceId)
        .subscribe(resp => {
          this.snackbar.open('Successfully updated resource', '', {
            duration: 2500
          });
        }, err => {
          console.log(err);
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
          this.getResources();
        });
    });
  }

  getDate(date: string): string {
    return new Date(date).toDateString();
  }

}
