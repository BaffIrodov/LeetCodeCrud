package net.lcc.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lcc.dto.EventDto;
import net.lcc.dto.EventStageDto;

@Entity
@Data
@NoArgsConstructor
public class EventStage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long eventId;
    private String name;
    private boolean archive = false;

    public EventStage(Long eventId, String name) {
        this.eventId = eventId;
        this.name = name;
    }

    public EventStage(EventStageDto eventStageDto) {
        this.id = eventStageDto.getId();
        this.eventId = eventStageDto.getEventId();
        this.name = eventStageDto.getName();
        this.archive = eventStageDto.isArchive();
    }

    public void update(EventStageDto eventStageDto) {
        this.eventId = eventStageDto.getEventId();
        this.name = eventStageDto.getName();
        this.archive = eventStageDto.isArchive();
    }

    public void archive() {
        this.archive = true;
    }
}
