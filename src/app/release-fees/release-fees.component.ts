import { Component } from '@angular/core';
import { SurveyorFees } from '../model/surveyorfees';
import { SurveyorService } from '../services/surveyor.service';

@Component({
  selector: 'app-release-fees',
  templateUrl: './release-fees.component.html',
  styleUrls: ['./release-fees.component.css']
})
export class ReleaseFeesComponent {
  claimId: string = '';
  releasedSurveyorFees: SurveyorFees | null = null;
 
  constructor(private surveyorService: SurveyorService) {}
  showError:string="";
 
  onSubmit(): void {
    this.showError = "";
    if (this.claimId.trim() !== '') {
      this.surveyorService.releaseSurveyorFees(this.claimId)
        .subscribe(
          (result: SurveyorFees) => {
            this.releasedSurveyorFees = result;
          },
          (error)=>{
            this.showError=error.error;
          }
        );
    }
  }
}