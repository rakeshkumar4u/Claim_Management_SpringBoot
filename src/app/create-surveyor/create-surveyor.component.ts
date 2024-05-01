import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Surveyor } from '../model/surveyor';
import { SurveyorService } from '../services/surveyor.service';
 
@Component({
  selector: 'app-create-surveyor',
  templateUrl: './create-surveyor.component.html',
  styleUrls: ['./create-surveyor.component.css']
})
export class CreateSurveyorComponent implements OnInit {
  surveyor: Surveyor = new Surveyor();
  constructor(private surveyorService: SurveyorService,
    private router: Router) { }
    showError:string="";
 
  ngOnInit(): void {
  }
 
  saveSurveyor(){
    this.showError = ""; //clear previous error message
    this.surveyorService.createSurveyor(this.surveyor).subscribe( data =>{
      console.log(data);
      alert("Surveyor Added Successfully");
      this.goToSurveyorList();
    },
      (error)=>{
        this.showError=JSON.stringify(error.error);
      }
    );
  }
 
  goToSurveyorList(){
    this.router.navigate(['/surveyors']);
  }
 
  onSubmit(){
    console.log(this.surveyor);
    this.saveSurveyor();
  }
 
}