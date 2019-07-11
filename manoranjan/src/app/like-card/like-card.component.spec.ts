import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LikeCardComponent } from './like-card.component';

describe('LikeCardComponent', () => {
  let component: LikeCardComponent;
  let fixture: ComponentFixture<LikeCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LikeCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LikeCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
