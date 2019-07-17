import { List } from 'lodash';

export class User {
    constructor(
  public emailId?:string,
  public  name?:string,
  public  age?:number,
  public gender?:string,
  public mobileNo?:string,
  public password?:string,
  public genre?:string
  // public genre?:List<string>
    )
    {}
}
