import {Resource} from './resource';
import {Employee} from './employee';

export class Team {
  public name: string;
  public size = 0;
  public monthlyCost = 0.0;
  public resourceResponses: Resource[];
  public employeeResponses: Employee[];
}
