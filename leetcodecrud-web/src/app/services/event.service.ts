import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { ConfigService } from "../config/config.service";
import { BaseService } from "./base.service";
import { firstValueFrom } from "rxjs";
import { Event } from "../dto/Event";

@Injectable({
  providedIn: "root"
})
export class EventService extends BaseService {

  constructor(private http: HttpClient,
              public router: Router,
              public override configService: ConfigService) {
    super(configService);
  }

  async getAllEvents(showArchive: boolean) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.get<Event[]>(url + "/event/all", {
      params: {
        showArchive: showArchive
      }
    }));
  }

  async getEvent(id: number) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.get<Event>(url + `/event/${id}`));
  }

  async createEvent(event: Event) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.post(url + "/event/create", event));
  }

  async updateEvent(id: number, event: Event) {
    const url = await this.getBackendUrl();
    return await firstValueFrom(this.http.put(url + `/event/${id}/update`, event));
  }

  async archiveEvent(id: number) {
    const url = await this.getBackendUrl();
    await firstValueFrom(this.http.delete(url + `/event/${id}/archive`));
  }
}
