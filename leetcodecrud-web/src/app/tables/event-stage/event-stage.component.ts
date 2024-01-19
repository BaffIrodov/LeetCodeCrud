import { Component, Input, ViewChild } from "@angular/core";
import { CellClickedEvent, ColDef, GridReadyEvent } from "ag-grid-community";
import { AgGridAngular } from "ag-grid-angular";
import { ConfirmationService, MessageService } from "primeng/api";
import { LoadingCellRendererComponent } from "../../platform/loading-cell-renderer/loading-cell-renderer.component";
import { Event } from "../../dto/Event";
import { EventStage } from "../../dto/EventStage";
import { EventStageService } from "../../services/event-stage.service";

@Component({
  selector: "app-event-stage",
  templateUrl: "./event-stage.component.html",
  styleUrls: ["./event-stage.component.scss"]
})
export class EventStageComponent {
  private _event: Event;
  filter: boolean = false;

  public get event(): Event {
    return this._event;
  }

  @Input("request")
  public set event(value: Event) {
    this._event = value;
    this.getAllEventStagesFromApi();
    this.selectedEventStage = new EventStage();
  }

  selectedEventStage: EventStage;

  showArchive = false;

  public columnDefs: ColDef[] = [
    { field: "id", headerName: "Идентификатор" },
    { field: "eventId", headerName: "Идентификатор ивента" },
    { field: "name", headerName: "Название" }
  ];

  // DefaultColDef sets props common to all Columns
  public defaultColDef: ColDef = {
    editable: false,
    sortable: true,
    flex: 1,
    minWidth: 100,
    filter: true,
    resizable: true,
    floatingFilter: this.filter
  };

  styles: {};
  // Data that gets displayed in the grid
  public rowData!: any[];

  editMode: boolean = false;
  openDialog: boolean = false;
  @ViewChild(AgGridAngular) agGrid!: AgGridAngular;
  public overlayLoadingTemplate = "<div class=\"loading-text\"> <span>L</span> <span>O</span> <span>A</span> <span>D</span> <span>I</span> <span>N</span> <span>G</span> </div> ";

  constructor(public eventStageService: EventStageService,
              private confirmationService: ConfirmationService,
              private messageService: MessageService) {
  }

  public loadingCellRenderer: any = LoadingCellRendererComponent;
  public loadingCellRendererParams: any = {
    loadingMessage: "Загузка..."
  };

  async onGridReady(params: GridReadyEvent) {
    if (this.event && this.event.id) {
      await this.getAllEventStagesFromApi();
    }
  }

  // Example of consuming Grid Event
  onCellClicked(e: CellClickedEvent): void {
    this.selectedEventStage = e.data;
  }

  async getAllEventStagesFromApi() {
    if (this.event && this.event.id) {
      this.agGrid.api.showLoadingOverlay();
      // const eventStages = await this.requestService.getRequestPositions(this.event.id, this.showArchive);
      this.rowData = [];
    }
  }

  showFilter() {
    this.filter = !this.filter;
    const columnDefs = this.agGrid.api.getColumnDefs();
    columnDefs?.forEach((colDef: any, index: number) => {
      colDef.floatingFilter = this.filter;
    });
    if (columnDefs) {
      this.agGrid.api.setColumnDefs(columnDefs);
    }
    this.agGrid.api.refreshHeader();
  }

  async onDialogSubmit($event: any) {
    this.openDialog = false;
    await this.getAllEventStagesFromApi();
  }

  createRequestPosition() {
    this.openDialog = true;
    this.editMode = false;
  }

  updateRequestPosition() {
    this.openDialog = true;
    this.editMode = true;
  }

  archiveRequestPosition() {
    // this.confirmationService.confirm({
    //   message: "Отправить позицию в архив?",
    //   accept: async () => {
    //     try {
    //       await this.requestService.archiveRequestPosition(this.selectedEventStage.id);
    //       this.messageService.add({
    //         severity: "success",
    //         summary: "Успех!",
    //         detail: "Позиция переведена в архив",
    //         life: 5000
    //       });
    //       await this.getAllEventStagesFromApi();
    //     } catch (e: any) {
    //       this.messageService.add({
    //         severity: "error",
    //         summary: "Ошибка...",
    //         detail: e.error.message,
    //         life: 5000
    //       });
    //     }
    //   },
    //   reject: () => {
    //     // can implement on cancel
    //   }
    // });
  }

  async showArchivePressed() {
    if (this.agGrid) {
      this.agGrid.columnApi.setColumnVisible("archive", this.showArchive);
    }
    await this.getAllEventStagesFromApi();
  }
}
