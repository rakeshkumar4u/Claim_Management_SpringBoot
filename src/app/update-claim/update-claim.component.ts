import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClaimService } from '../services/claim.service';
import { Claim } from '../model/claim';
import { Surveyor } from '../model/surveyor';

@Component({
  selector: 'app-update-claim',
  templateUrl: './update-claim.component.html',
  styleUrls: ['./update-claim.component.css']
})
export class UpdateClaimComponent implements OnInit {
  today: Date = new Date();
  claimId: string;
  claim: Claim = new Claim();
  surveyor: Surveyor = new Surveyor;
  constructor(private claimService: ClaimService,
    private route: ActivatedRoute,
    private router: Router) { }

  showError: string = "";

  ngOnInit(): void {

    this.claim.surveyor = this.surveyor;
    this.claimId = this.route.snapshot.params['claimId'];

    this.claimService.getClaimById(this.claimId).subscribe(data => {
      this.claim = data;
    }, error => console.log(error));
  }

  onSubmit() {
    this.claimService.updateClaim(this.claimId, this.claim).subscribe(data => {
      alert("Updated Successfully");
      this.goToClaimList();
    },
      (error) => {
        this.showError = JSON.stringify(error.error);
      }
    );
  }

  goToClaimList() {
    this.router.navigate(['/claims']);
  }
}
