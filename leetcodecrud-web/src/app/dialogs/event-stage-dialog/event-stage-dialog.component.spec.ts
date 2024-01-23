import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventStageDialogComponent } from './event-stage-dialog.component';

describe('RequestPositionDialogComponent', () => {
  let component: EventStageDialogComponent;
  let fixture: ComponentFixture<EventStageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventStageDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventStageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
