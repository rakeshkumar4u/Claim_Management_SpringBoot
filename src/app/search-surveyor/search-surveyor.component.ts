import { Component, OnInit } from '@angular/core';
import { Surveyor } from '../model/surveyor';
import { SurveyorService } from '../services/surveyor.service';

@Component({
  selector: 'app-search-surveyor',
  templateUrl: './search-surveyor.component.html',
  styleUrls: ['./search-surveyor.component.css']
})
export class SearchSurveyorComponent implements OnInit {
  surveyors: Surveyor[] = [];
  estimatedLoss: number;
 
  constructor(private surveyorService: SurveyorService) {}
  showError:string="";
 
  ngOnInit(): void {}
 
  searchSurveyors(): void {
    this.showError = "";
    if (this.estimatedLoss) {
      this.surveyorService.getSurveyorByEstimatedLoss(this.estimatedLoss).subscribe(
        (surveyors) => {
          this.surveyors = surveyors;
        },
        (error) => {
          this.showError=error.error;
        }
      );
    }
  }
}