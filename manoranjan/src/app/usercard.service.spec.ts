import { TestBed } from '@angular/core/testing';

import { UsercardService } from './usercard.service';

describe('UsercardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UsercardService = TestBed.get(UsercardService);
    expect(service).toBeTruthy();
  });
});
