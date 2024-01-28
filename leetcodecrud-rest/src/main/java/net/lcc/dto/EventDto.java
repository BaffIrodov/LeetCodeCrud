package net.lcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.lcc.entities.Event;

@Data
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private boolean archive;

    public EventDto(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.archive = event.isArchive();
    }
}
