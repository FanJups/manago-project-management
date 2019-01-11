import {Status} from './status';

export class Task {
  public taskId: number;
  public name: string;
  public parentId: number = null;
  public statusResponse: Status = null;
  public subTaskResponses: Task[] = [];
}
