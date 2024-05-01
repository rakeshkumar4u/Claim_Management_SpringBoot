import { Policy } from "./policy";
import { Surveyor } from "./surveyor";


export class Claim {
    claimId:string;
    estimatedLoss:number;
    dateOfAccident:Date;
    claimStatus:boolean;
    amtApprovedBySurveyor:number;
    insuranceCompanyApproval:boolean;
    withdrawClaim:boolean;
    surveyorFees:number;
    surveyor:Surveyor;
    policy:Policy;
   
}
