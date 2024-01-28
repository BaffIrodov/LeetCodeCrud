import { Component, EventEmitter, Input, Output } from "@angular/core";
import { MessageService } from "primeng/api";
import { EventStageService } from "../../services/event-stage.service";
import { EventStage } from "../../dto/EventStage";

@Component({
  selector: "app-event-stage-dialog",
  templateUrl: "./event-stage-dialog.component.html",
  styleUrls: ["./event-stage-dialog.component.scss"]
})
export class EventStageDialogComponent {

  @Input("openDialog") visible: boolean = false;
  @Input("item") item: EventStage = new EventStage();
  @Input("editMode") editMode: boolean;
  @Input("eventId") eventId: number;
  @Output() submit = new EventEmitter<any>();
  @Output() visibleChange = new EventEmitter<any>();
  dialogTitle = "Регистрация ребенка";
  products: any[] = [];

  constructor(private eventStageService: EventStageService,
              public messageService: MessageService) {
  }

  async ngOnInit() {
    if (this.editMode) {
      this.dialogTitle = "Редактирование ребенка";
    } else {
      this.item = new EventStage();
      this.dialogTitle = "Регистрация ребенка";
    }
    if (!!this.eventId) {
      this.item.eventId = this.eventId;
    }
  }

  async onSubmit($event: any) {
    if ($event !== null) { // null передается, если закрыть форму без сохранения на крестик
      if (this.editMode) {
        await this.updateRequestPosition($event);
      } else {
        await this.createRequestPosition($event);
      }
    }
    this.submit.emit();
    this.visible = false;
  }

  async createRequestPosition(eventStage: EventStage) {
    try {
      await this.eventStageService.createEventStage(eventStage);
      this.messageService.add({
        severity: "success",
        summary: "Успех!",
        detail: "Дефолт ребенок заведен",
        life: 5000
      });
    } catch (e: any) {
      this.messageService.add({
        severity: "error",
        summary: "Ошибка...",
        detail: e.error.message,
        life: 5000
      });
    }
  }

  async updateRequestPosition(eventStage: EventStage) {
    try {
      await this.eventStageService.updateEventStage(eventStage.id, eventStage);
      this.messageService.add({
        severity: "success",
        summary: "Успех!",
        detail: "Дефолт ребенок обновлен",
        life: 5000
      });
    } catch (e: any) {
      this.messageService.add({
        severity: "error",
        summary: "Ошибка...",
        detail: e.error.message,
        life: 5000
      });
    }
  }
}
