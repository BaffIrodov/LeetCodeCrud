import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Event } from "../../dto/Event";
import { MessageService } from "primeng/api";
import { EventService } from "../../services/event.service";

@Component({
  selector: "app-event-dialog",
  templateUrl: "./event-dialog.component.html",
  styleUrls: ["./event-dialog.component.scss"]
})
export class EventDialogComponent implements OnInit {

  @Input("openDialog") visible: boolean = false;
  @Input("item") item: Event = new Event();
  @Input("editMode") editMode: boolean;
  @Output() submit = new EventEmitter<any>();
  @Output() visibleChange = new EventEmitter<any>();
  dialogTitle = "Регистрация дефолт-родителя";

  constructor(private eventService: EventService,
              public messageService: MessageService) {
  }

  ngOnInit(): void {
    if (this.editMode) {
      this.dialogTitle = "Редактирование дефолт-родителя";
    } else {
      this.item = new Event();
      this.dialogTitle = "Регистрация дефолт-родителя";
    }
  }

  async onSubmit($event: any) {
    if ($event !== null) { // null передается, если закрыть форму без сохранения на крестик
      if (this.editMode) {
        await this.updateEvent($event);
      } else {
        await this.createEvent($event);
      }
    }
    this.submit.emit($event);
    this.visible = false;
  }

  async createEvent(event: Event) {
    try {
      const rq = await this.eventService.createEvent(event);
      this.messageService.add({
        severity: "success",
        summary: "Успех!",
        detail: "Дефолт родитель заведен",
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

  async updateEvent(event: Event) {
    try {
      const rq = await this.eventService.updateEvent(event.id, event);
      this.messageService.add({
        severity: "success",
        summary: "Успех!",
        detail: "Дефолт родитель обновлён",
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
