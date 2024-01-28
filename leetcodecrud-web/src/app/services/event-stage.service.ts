import { Injectable } from '@angular/core';
import { firstValueFrom } from "rxjs";
import { Event } from "../dto/Event";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { ConfigService } from "../config/config.service";
import { BaseService } from "./base.service";
import { EventStage } from "../dto/EventStage";

@Injectable({
  providedIn: 'root'
})
export class EventStageService extends BaseService {

  constructor(private http: HttpClient,
              public router: Router,
              public override configService: ConfigService) {
    super(configService);
  }

  async getAllEventStages(showArchive: boolean) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.get<Event[]>(url + "/event-stage/all", {
      params: {
        showArchive: showArchive
      }
    }));
  }

  async getEventStageByEventId(id: number, showArchive: boolean) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.get<EventStage[]>(url + `/event-stage/${id}`, {
      params: {
        showArchive: showArchive
      }
    }));
  }

  async createEventStage(eventStage: EventStage) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.post<EventStage[]>(url + "/event-stage/create", eventStage));
  }

  async updateEventStage(id: number, eventStage: EventStage) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.put<EventStage[]>(url + `/event-stage/${id}/update`, eventStage));
  }

  async archiveEventStage(id: number) {
    const url = await this.getBackendUrl();
    await firstValueFrom(this.http.delete(url + `/event-stage/${id}/archive`));
  }
}
