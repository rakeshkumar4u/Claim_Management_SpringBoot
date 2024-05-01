import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReleaseFeesComponent } from './release-fees.component';

describe('ReleaseFeesComponent', () => {
  let component: ReleaseFeesComponent;
  let fixture: ComponentFixture<ReleaseFeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReleaseFeesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReleaseFeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
