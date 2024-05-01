import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Surveyor } from '../surveyor';
import { SurveyorService } from '../surveyor.service';
 
@Component({
  selector: 'app-create-surveyor',
  templateUrl: './create-surveyor.component.html',
  styleUrls: ['./create-surveyor.component.css']
})
export class CreateSurveyorComponent {
  surveyor: Surveyor = new Surveyor();
  showError: string | null = null;
 
  constructor(private surveyorService: SurveyorService, private router: Router) {}
 
  saveSurveyor() {
    this.showError = null; // Clear previous error message
    this.surveyorService.createSurveyor(this.surveyor).subscribe(
      (data) => {
        console.log(data);
        alert('Surveyor Added Successfully');
        this.goToSurveyorList();
      },
      (error) => {
        console.error('Error creating surveyor:', error);
        this.showError = error; // Display the error message
      }
    );
  }
 
  goToSurveyorList() {
    this.router.navigate(['/surveyors']);
  }
 
  onSubmit() {
    console.log(this.surveyor);
    this.saveSurveyor();
  }
}