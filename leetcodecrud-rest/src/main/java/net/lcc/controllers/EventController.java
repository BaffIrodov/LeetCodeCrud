package net.lcc.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.lcc.dto.EventDto;
import net.lcc.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("all")
    public List<EventDto> getAllEvents(@RequestParam boolean showArchive) {
        return eventService.getAllEvents(showArchive);
    }

    @GetMapping("{id}")
    public EventDto getEvent(@PathVariable("id") Long id) {
        return eventService.getEvent(id);
    }

    @PostMapping("create")
    @Transactional
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return this.eventService.createEvent(eventDto);
    }

    @PutMapping("{id}/update")
    @Transactional
    public EventDto updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        return this.eventService.updateEvent(id, eventDto);
    }

    @DeleteMapping("{id}/archive")
    @Transactional
    public void archiveEvent(@PathVariable Long id) {
        this.eventService.archiveEvent(id);
    }
}
