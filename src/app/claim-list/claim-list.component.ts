import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Claim } from '../model/claim';
import { ClaimService } from '../services/claim.service';

@Component({
  selector: 'app-claim-list',
  templateUrl: './claim-list.component.html',
  styleUrls: ['./claim-list.component.css']
})
export class ClaimListComponent { 
  claims:Claim[];

  constructor(private claimService:ClaimService,
    private router:Router){
      this.getClaims(); 
    }

  private getClaims(){
    this.claimService.getClaimList().subscribe(data=>{
      this.claims=data;
    });
  }

  updateClaim(claimId: string){
    this.router.navigate(['update-claim', claimId]);
  }

}
