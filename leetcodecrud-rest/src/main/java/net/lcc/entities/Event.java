package net.lcc.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lcc.dto.EventDto;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private boolean archive = false;

    public Event(String name) {
        this.name = name;
    }

    public Event(EventDto eventDto) {
        this.id = eventDto.getId();
        this.name = eventDto.getName();
        this.archive = eventDto.isArchive();
    }

    public void update(EventDto eventDto) {
        this.name = eventDto.getName();
        this.archive = eventDto.isArchive();
    }

    public void archive() {
        this.archive = true;
    }
}
