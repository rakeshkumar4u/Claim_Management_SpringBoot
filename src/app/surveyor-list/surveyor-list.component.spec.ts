import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyorListComponent } from './surveyor-list.component';

describe('SurveyorListComponent', () => {
  let component: SurveyorListComponent;
  let fixture: ComponentFixture<SurveyorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyorListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
