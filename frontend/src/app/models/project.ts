import {Customer} from './customer';
import {Team} from './team';

export class Project {
  public name: string;
  public description: string;
  public customerResponses: Customer[];
  public teamResponse: Team;
}
