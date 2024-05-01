import { Component, OnInit } from '@angular/core';
import { Policy } from '../model/policy';
import { PolicyService } from '../services/policy.service';

@Component({
  selector: 'app-policy-list',
  templateUrl: './policy-list.component.html',
  styleUrls: ['./policy-list.component.css']
})
export class PolicyListComponent {
  policies:Policy[];
  constructor(private policyService:PolicyService){
    this.getPolicies();
  }

  private getPolicies(){
    this.policyService.getPolicyList().subscribe(data=>{
      this.policies=data;
    });
  }
}
