package net.lcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.lcc.entities.EventStage;

@Data
@NoArgsConstructor
public class EventStageDto {
    private Long id;
    private Long eventId;
    private String name;

    public EventStageDto(EventStage eventStage) {
        this.id = eventStage.getId();
        this.eventId = eventStage.getEventId();
        this.name = eventStage.getName();
    }
}
