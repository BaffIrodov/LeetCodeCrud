import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventStageComponent } from './event-stage.component';

describe('EventStageComponent', () => {
  let component: EventStageComponent;
  let fixture: ComponentFixture<EventStageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventStageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventStageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
