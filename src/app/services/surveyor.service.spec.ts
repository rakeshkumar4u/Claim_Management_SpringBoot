import { TestBed } from '@angular/core/testing';

import { SurveyorService } from './surveyor.service';

describe('SurveyorService', () => {
  let service: SurveyorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveyorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
