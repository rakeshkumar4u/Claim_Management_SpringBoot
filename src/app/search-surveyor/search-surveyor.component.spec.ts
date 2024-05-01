import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchSurveyorComponent } from './search-surveyor.component';

describe('SearchSurveyorComponent', () => {
  let component: SearchSurveyorComponent;
  let fixture: ComponentFixture<SearchSurveyorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchSurveyorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchSurveyorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
