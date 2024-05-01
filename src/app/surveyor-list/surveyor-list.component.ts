import { Component, OnInit } from '@angular/core';
import { Surveyor } from '../model/surveyor';
import { SurveyorService } from '../services/surveyor.service';

@Component({
  selector: 'app-surveyor-list',
  templateUrl: './surveyor-list.component.html',
  styleUrls: ['./surveyor-list.component.css']
})
export class SurveyorListComponent{
  surveyors:Surveyor[];
  constructor(private surveyorService:SurveyorService) {
    this.getSurveyors(); // call in constructor to ensure that method called when component is instantiated
  }
 
  private getSurveyors(){
    this.surveyorService.getSurveyorList().subscribe(data=>{
      this.surveyors=data;
    });
  }
}

