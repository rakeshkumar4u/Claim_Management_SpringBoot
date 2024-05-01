import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Policy } from '../model/policy';

@Injectable({
  providedIn: 'root'
})
export class PolicyService {
  private baseURL="http://localhost:8080/api/policies/";
  constructor(private httpClient:HttpClient) { }

  getPolicyList():Observable<Policy[]>{
    return this.httpClient.get<Policy[]>(`${this.baseURL}`);
  }

  createPolicy(policy:Policy):Observable<any>{
    return this.httpClient.post(`${this.baseURL}`,policy);
  }
}
