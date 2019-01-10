import { Component, OnInit } from '@angular/core';
import {Task} from '../../../models/task';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../../../services/project.service';
import {TaskService} from '../../../services/task.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  task: Task = new Task();
  subtasks: Task[];
  displayedColumns = ['taskId', 'name' , 'edit', 'detail', 'delete'];

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectService,
    private taskService: TaskService,
    private router: Router
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

  showSubTask(subtask: Task): void {
    this.router.navigate(['projects', this.route.params['value'].projectName, 'tasks', subtask.taskId]);
  }

  editSubTaskDialog(subtask: Task): void {

  }

  deleteSubTask(subtask: Task): void {

  }

}
