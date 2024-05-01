import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Policy } from '../model/policy';
import { PolicyService } from '../services/policy.service';

@Component({
  selector: 'app-create-policy',
  templateUrl: './create-policy.component.html',
  styleUrls: ['./create-policy.component.css']
})
export class CreatePolicyComponent implements OnInit {
  today: Date = new Date();
  policy: Policy = new Policy();
  constructor(private policyService: PolicyService,
    private router: Router) { }
    showError:string="";

  ngOnInit(): void {
  }

  savePolicy(){
    this.showError = "";
    this.policyService.createPolicy(this.policy).subscribe( data =>{
      alert("Policy Created Successfully");
      console.log(data);
      this.goToPolicyList();
    },
    (error)=>{
      this.showError=JSON.stringify(error.error); //error payload receives from server
    }
    );
  }

  goToPolicyList(){
    this.router.navigate(['/policies']);
  }
  
  onSubmit(){
    console.log(this.policy);
    this.savePolicy();
  }


}
