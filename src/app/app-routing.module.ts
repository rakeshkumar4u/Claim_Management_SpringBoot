import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClaimListComponent } from './claim-list/claim-list.component';
import { CreateClaimComponent } from './create-claim/create-claim.component';
import { CreatePolicyComponent } from './create-policy/create-policy.component';
import { CreateSurveyorComponent } from './create-surveyor/create-surveyor.component';
import { PolicyListComponent } from './policy-list/policy-list.component';
import { ReleaseFeesComponent } from './release-fees/release-fees.component';
import { SearchSurveyorComponent } from './search-surveyor/search-surveyor.component';
import { SurveyorListComponent } from './surveyor-list/surveyor-list.component';
import { UpdateClaimComponent } from './update-claim/update-claim.component';

const routes: Routes = [
  { path:'surveyors',component:SurveyorListComponent },
  {path:'create-surveyor',component:CreateSurveyorComponent},
  {path:'search-surveyor',component:SearchSurveyorComponent},
  {path:'release-fees',component:ReleaseFeesComponent},
  {path:'',redirectTo:'surveyors',pathMatch:'full'},

  {path:'policies',component:PolicyListComponent},
  {path:'create-policy',component:CreatePolicyComponent},
  {path:'',redirectTo:'policies',pathMatch:'full'},

  {path:'claims',component:ClaimListComponent},
  {path:'create-claim',component:CreateClaimComponent},
  {path:'',redirectTo:'claims',pathMatch:'full'},
  {path:'update-claim/:claimId',component:UpdateClaimComponent} //dynamic routing
 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
