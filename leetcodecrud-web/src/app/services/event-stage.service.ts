import { Injectable } from '@angular/core';
import { firstValueFrom } from "rxjs";
import { Event } from "../dto/Event";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { ConfigService } from "../config/config.service";
import { BaseService } from "./base.service";

@Injectable({
  providedIn: 'root'
})
export class EventStageService extends BaseService {

  constructor(private http: HttpClient,
              public router: Router,
              public override configService: ConfigService) {
    super(configService);
  }

  async getAllEventStages() {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.get<Event[]>(url + "/event-stage/all"));
  }

  async getEventStage(id: number) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.get<Event>(url + `/event-stage/${id}`));
  }

  async createEventStage(event: Event) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.post(url + "/event-stage/create", event));
  }

  async updateEventStage(id: number, event: Event) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.put(url + `/event-stage/${id}/update`, event));
  }

  async archiveEventStage(id: number) {
    const url = await this.getBackendUrl();
    await firstValueFrom(this.http.delete(url + `/event-stage/${id}/archive`));
  }
}
