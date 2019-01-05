import {Project} from './project';

export class Customer {
  public customerId: number;
  public firstName: string;
  public lastName: string;
  public email: string;
  public company: string;
  public address: string;
  public zipCode: string;
  public city: string;
  public projectResponses: Project[] = null;
}
