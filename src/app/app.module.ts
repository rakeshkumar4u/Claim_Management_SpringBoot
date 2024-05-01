import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SurveyorListComponent } from './surveyor-list/surveyor-list.component';
import { CreateSurveyorComponent } from './create-surveyor/create-surveyor.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PolicyListComponent } from './policy-list/policy-list.component';
import { CreatePolicyComponent } from './create-policy/create-policy.component';
import { CreateClaimComponent } from './create-claim/create-claim.component';
import { ClaimListComponent } from './claim-list/claim-list.component';
import { SearchSurveyorComponent } from './search-surveyor/search-surveyor.component';
import { ReleaseFeesComponent } from './release-fees/release-fees.component';
import { UpdateClaimComponent } from './update-claim/update-claim.component';


@NgModule({
  declarations: [
    AppComponent,
    SurveyorListComponent,
    CreateSurveyorComponent,
    PolicyListComponent,
    CreatePolicyComponent,
    ClaimListComponent,
    UpdateClaimComponent,
    CreateClaimComponent,
    SearchSurveyorComponent,
    ReleaseFeesComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
