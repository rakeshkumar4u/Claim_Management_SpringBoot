import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Surveyor } from '../model/surveyor';
import { SurveyorFees } from '../model/surveyorfees';

@Injectable({
  providedIn: 'root'
})
export class SurveyorService {
 
  private baseURL="http://localhost:8080/api/surveyors/";
  constructor(private httpClient:HttpClient) { }
 
  getSurveyorList():Observable<Surveyor[]>{
    return this.httpClient.get<Surveyor[]>(`${this.baseURL}`);
  }
 
  createSurveyor(surveyor:Surveyor):Observable<any>{
    return this.httpClient.post(`${this.baseURL}`,surveyor);
  }


  getSurveyorByEstimatedLoss(estimatedLoss: number): Observable<Surveyor[]> {
    return this.httpClient.get<Surveyor[]>(`${this.baseURL}${estimatedLoss}`);
  }

  releaseSurveyorFees(claimId: string): Observable<SurveyorFees> {
    const url = `${this.baseURL}${claimId}`;
 
    return this.httpClient.put<SurveyorFees>(url,null);
  }
}
