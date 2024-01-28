package net.lcc.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.lcc.dto.EventStageDto;
import net.lcc.services.EventStageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event-stage")
@RequiredArgsConstructor
public class EventStageController {
    private final EventStageService eventStageService;

    @GetMapping("all")
    public List<EventStageDto> getAllEventStages(@RequestParam boolean showArchive) {
        return eventStageService.getAllEventStages(showArchive);
    }

    @GetMapping("{id}")
    public List<EventStageDto> getEventStagesByEventId(@PathVariable("id") Long id,
                                       @RequestParam boolean showArchive) {
        return eventStageService.getEventStagesByEventId(id, showArchive);
    }

    @PostMapping("create")
    @Transactional
    public List<EventStageDto> createEventStage(@RequestBody EventStageDto eventStageDto) {
        return this.eventStageService.createEventStage(eventStageDto);
    }

    @PutMapping("{id}/update")
    @Transactional
    public List<EventStageDto> updateEventStage(@PathVariable Long id, @RequestBody EventStageDto eventStageDto) {
        return this.eventStageService.updateEventStage(id, eventStageDto);
    }

    @DeleteMapping("{id}/archive")
    @Transactional
    public void archiveEvent(@PathVariable Long id) {
        this.eventStageService.archiveEventStage(id);
    }
}
