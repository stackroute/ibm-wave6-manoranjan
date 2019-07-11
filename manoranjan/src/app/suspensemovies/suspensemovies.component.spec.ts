import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuspensemoviesComponent } from './suspensemovies.component';

describe('SuspensemoviesComponent', () => {
  let component: SuspensemoviesComponent;
  let fixture: ComponentFixture<SuspensemoviesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuspensemoviesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuspensemoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
