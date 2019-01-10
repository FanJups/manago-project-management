import {Component, Input, OnInit} from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Task} from '../../models/task';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskService} from '../../services/task.service';
import {ProjectEditComponent} from '../projects/project-edit/project-edit.component';
import {TaskEditComponent} from './task-edit/task-edit.component';
import {CustomerEditComponent} from '../customers/customer-edit/customer-edit.component';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  tasks: MatTableDataSource<Task> = new MatTableDataSource<Task>();
  // displayedColumns = ['taskId', 'subtaskCount', 'name' , 'status', 'edit', 'detail', 'delete'];
  displayedColumns = ['taskId', 'subtaskCount', 'name', 'edit', 'detail', 'delete'];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar,
    private taskService: TaskService
  ) { }

  ngOnInit() {
    this.getTasks();
  }

  showTask(task: Task): void {
    this.router.navigate(['projects', this.route.params['value'].projectName, 'tasks', task.taskId]);
  }

  editTaskDialog(task: Task): void {
    const dialogRef = this.dialog.open(TaskEditComponent, {
      width: '350px',
      data: { edit: true,
        name: task.name,
        status: task.status,
        availableTasks: this.tasks.data.slice().filter(t => t.taskId !== task.taskId ),
        availableStatuses: []}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.taskService.updateTask(
        {name: result.name, subtaskResponses: task.subTaskResponses, status: result.status, parentId: task.parentId},
        this.route.params['value'].projectName,
        task.taskId.toString())
        .subscribe(resp => {
          this.snackbar.open('Successfully updated project', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open('Could not update project', '', {
            duration: 2500
          });
        }, () => {
          this.getTasks();
        });
    });
  }

  createTaskDialog(): void {
    const dialogRef = this.dialog.open(TaskEditComponent, {
      width: '350px',
      data: {  edit: false,
        name: '',
        status: '',
        availableStatuses: []}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.taskService.createTask({name: result.name, subtaskResponses: [], status: result.status, parentId: null},
        this.route.params['value'].projectName)
        .subscribe(resp => {
          this.snackbar.open('Successfully created new task', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
          this.getTasks();
        });
    });
  }

  deleteTask(task: Task): void {
    this.taskService.deleteTask(this.route.params['value'].projectName, task.taskId.toString()).subscribe(resp => {
      this.snackbar.open('Successfully removed task', '', {
        duration: 2500
      });
    }, err => {
      console.log(err);
      this.snackbar.open(err, '', {
        duration: 10000
      });
    }, () => {
      this.getTasks();
    });
  }

  getTasks(): void {
    this.taskService.getTasks(this.route.params['value'].projectName).subscribe(resp => {
      console.log(resp);
      this.tasks.data = resp;
      console.log(this.tasks.data);
    });
  }

  getSubtaskLength(task: Task): number {
    return task.subTaskResponses ? task.subTaskResponses.length : 0;
  }

}
