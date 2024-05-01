
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Claim } from '../model/claim';
 
@Injectable({
  providedIn: 'root'
})
export class ClaimService {
  private baseURL='http://localhost:8080/api/claims/';
  constructor(private httpClient:HttpClient) { }
 
  getClaimList():Observable<Claim[]>{
    return this.httpClient.get<Claim[]>(this.baseURL);
  }
 
  createClaim(claim:Claim):Observable<any>{
    console.log(claim);
    return this.httpClient.post(this.baseURL,claim);
  }
 
  getClaimById(claimId: string): Observable<Claim>{
    return this.httpClient.get<Claim>(`${this.baseURL}${claimId}`);
  }
 
  updateClaim(claimId: string, claim: Claim): Observable<any>{
    return this.httpClient.put(`${this.baseURL}${claimId}`,claim);
  }
}
 