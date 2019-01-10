import {ResourceType} from './resourceType';

export class Resource {
  public resourceId: number;
  public name: string;
  public cost: number;
  public manufacturer: number;
  public boughtAt: string;
  public resourceType: ResourceType;
}
