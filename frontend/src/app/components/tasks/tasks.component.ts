import {Component, Input, OnInit} from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Task} from '../../models/task';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskService} from '../../services/task.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  tasks: MatTableDataSource<Task> = new MatTableDataSource<Task>();
  displayedColumns = ['taskId', 'name' , 'edit', 'detail', 'delete'];

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

  }

  editTaskDialog(task: Task): void {

  }

  deleteTask(task: Task): void {

  }

  getTasks(): void {
    this.taskService.getTasks(this.route.params['value'].projectName).subscribe(resp => {
      console.log(resp);
      this.tasks.data = resp;
      console.log(this.tasks.data);
    });
  }

}
