import { List } from 'lodash';

export class User {
    emailId:string;
    name:string;
    age:number;
    gender:string;
    mobileNo:string;
    password:string;
    genre:List<string>;
}
