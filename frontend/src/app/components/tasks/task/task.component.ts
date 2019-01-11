import { Component, OnInit } from '@angular/core';
import {Task} from '../../../models/task';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../../services/project.service';
import {TaskService} from '../../../services/task.service';
import {TaskEditComponent} from '../task-edit/task-edit.component';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {HistoryComponent} from '../../history/history.component';
import {HistoryService} from '../../../services/history.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  task: Task = new Task();
  subtasks: Task[];
  displayedColumns = ['taskId', 'name' , 'status', 'history', 'edit', 'delete'];

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService,
    private taskService: TaskService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar,
    private historyService: HistoryService
  ) { }

  ngOnInit() {
    this.getTask(
      this.route.params['value'].projectName,
      this.route.params['value'].taskId.toString()
    );
  }

  getTask(projectName: string, taskId: string): void {
    this.taskService.getTask(projectName, taskId).subscribe(resp => {
      this.task = resp;
      this.subtasks = this.task.subTaskResponses.slice();
      console.log(resp);
    });
  }

  navigateToParent(task: Task): void {
    this.router.navigate(['projects', this.route.params['value'].projectName, 'tasks', this.route.params['value'].taskId]);
  }

  editSubTaskDialog(subtask: Task): void {
    const dialogRef = this.dialog.open(TaskEditComponent, {
      width: '350px',
      data: { edit: true,
        name: subtask.name,
        subtasks: subtask.subTaskResponses,
        status: subtask.status,
        availableStatuses: []}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.taskService.updateTask(
        {name: result.name, subtaskResponses: '', status: result.status, parentId: subtask.parentId},
        this.route.params['value'].projectName,
        subtask.taskId.toString())
        .subscribe(resp => {
          this.snackbar.open('Successfully updated task', '', {
            duration: 2500
          });
        }, err => {
          console.log(err);
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
         this.getTask(
            this.route.params['value'].projectName,
            this.route.params['value'].taskId.toString()
          );
        });
    });
  }

  createSubTaskDialog(): void {
    const dialogRef = this.dialog.open(TaskEditComponent, {
      width: '350px',
      data: {  edit: false,
        name: '',
        status: '',
        availableStatuses: []}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.taskService.createTask({name: result.name, subtaskResponses: [], status: result.status, parentId: this.task.taskId},
        this.route.params['value'].projectName)
        .subscribe(resp => {
          this.snackbar.open('Successfully created new subtask', '', {
            duration: 2500
          });
        }, err => {
          console.log(err)
          this.snackbar.open(err, '', {
            duration: 10000
          });
        }, () => {
          this.getTask(
            this.route.params['value'].projectName,
            this.route.params['value'].taskId.toString()
          );
        });
    });
  }

  onHistoryClick(task: Task): void {
    this.historyService.getHistory(task.taskId.toString()).subscribe(resp => {
      const dialogRef = this.dialog.open(HistoryComponent, {
        width: '500px',
        data: {  edit: false,
          history: new MatTableDataSource()}
      });
    });
  }

  deleteSubTask(subtask: Task): void {
    this.taskService.deleteTask(this.route.params['value'].projectName,
      subtask.taskId.toString()).subscribe(resp => {
      this.snackbar.open('Successfully removed subtask', '', {
        duration: 2500
      });
    }, err => {
      console.log(err);
      this.snackbar.open(err, '', {
        duration: 10000
      });
    }, () => {
      this.getTask(
        this.route.params['value'].projectName,
        this.route.params['value'].taskId.toString()
      );
    });
  }

}
