import { TestBed } from '@angular/core/testing';

import { EventStageService } from './event-stage.service';

describe('EventStageService', () => {
  let service: EventStageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventStageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
