import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ignoreElements } from 'rxjs';
import { Claim } from '../model/claim';
import { Policy } from '../model/policy';
import { Surveyor } from '../model/surveyor';
import { ClaimService } from '../services/claim.service';
;


@Component({
  selector: 'app-create-claim',
  templateUrl: './create-claim.component.html',
  styleUrls: ['./create-claim.component.css']
})

export class CreateClaimComponent {
  today: Date = new Date();
  claim:Claim= new Claim();
  surveyor:Surveyor=new Surveyor();
  policy:Policy=new Policy();

  constructor(private claimService: ClaimService,
    private router: Router) { }
    showError:string="";

  ngOnInit(): void {
    this.claim.policy=this.policy;
    this.claim.surveyor=this.surveyor;
  }
  
  saveClaim():void{
    this.showError = "";
    this.claimService.createClaim(this.claim).subscribe(
       (data) =>{
        alert("Claim Added Successfully");
        console.log('Claim Added Successfully:',data);
        this.goToClaimList();
    },
    (error)=>{
      this.showError=JSON.stringify(error.error);
    }
  );
}

  goToClaimList():void{
    this.router.navigate(['/claims']);
  }
  
  onSubmit():void{
    this.saveClaim();
  }

}
