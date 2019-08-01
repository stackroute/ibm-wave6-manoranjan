import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StandaloneMediaComponent } from './standalone-media.component';

describe('StandaloneMediaComponent', () => {
  let component: StandaloneMediaComponent;
  let fixture: ComponentFixture<StandaloneMediaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StandaloneMediaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StandaloneMediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
