import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import { FormBuilder, Form } from '@angular/forms';
import{UsercardService} from '../usercard.service';
import { Usercard } from '../usercard';
import { Userpayment } from '../userpayment';
import{PaymentService}from '../payment.service';
import { Cardinfo } from '../cardinfo';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
ptime;
  user= new User();
  payment=new Userpayment();
  emailId;packageName;
   time;
   amount; ddMMyyyy;
  cardName;cardNumber;expiryMonth;expiryYear;cvv;
   route:any;
  usercard:Usercard=new Usercard();
  form: Form;
  cardinfo=new Cardinfo();
  submitted: boolean;
  registerForm: any;
  constructor(private _formBuilder: FormBuilder,private router:Router,private usercardservice:UsercardService,
    private activatedRoute:ActivatedRoute,private paymentservice:PaymentService,public dialog: MatDialog,private datePipe: DatePipe) {
       this.ddMMyyyy = this.datePipe.transform(new Date(),"dd-MM-yyyy");
      }
    submit(time,amount,cardName,cardNumber,expiryMonth,expiryYear,cvv){
      this.time=time;
      this.cardinfo.cardName=cardName;
      this.cardinfo.cardNumber=cardNumber;
      this.cardinfo.expiryMonth=expiryMonth;
      this.cardinfo.expiryYear=expiryYear;
      this.cardinfo.cvv=cvv;
      let form = document.getElementsByTagName("form")[0];
          (<any>window).Stripe.card.createToken({
            number:this.cardinfo.cardNumber,
            exp_month: this.cardinfo.expiryMonth,
            exp_year: this.cardinfo.expiryYear,
            cvc: this.cardinfo.cvv
      }, (status:number, response: any) => {
            if (status === 200) {
             let token = response.id;
              console.log(token);
            this.payment.emailId=this.user.emailId;
            this.payment.packageName=time;
            sessionStorage.setItem("packageTime",this.payment.packageName)
            this.payment.mydate=this.ddMMyyyy;
      this.paymentservice.save(this.payment).
        subscribe(
            data => {
              this.ptime=sessionStorage.getItem('packageTime');
              //this.router.navigateByUrl('/paymentsuccess/'+this.ptime+'/'+amount);
              console.log("POST Request is successful ", data)
            },
       error => {
        // alert("incorrect details")
        console.log("1234567")
        this.router.navigate(['/payment/'+time+'/'+amount],{relativeTo:this.route});
        console.log("Error", error);}
      );
          // this.paymentservice.chargeCard(token);
          console.log("fdsad")
        }
    else {
          console.log(response.error.message);
        }
      });
     }
  ngOnInit() {
    this.user.emailId=sessionStorage.getItem('email');
    console.log( this.user.emailId);
    this.time=sessionStorage.getItem('time');
    this.activatedRoute.paramMap.subscribe(params=>{
      this.time=params.get('time');
      this.amount=params.get('amount');
      console.log("time- "+this.time+" amount- "+this.amount );
    });
  }
}
  


