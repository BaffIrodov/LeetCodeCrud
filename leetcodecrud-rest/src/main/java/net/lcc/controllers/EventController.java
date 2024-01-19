package net.lcc.controllers;

import lombok.RequiredArgsConstructor;
import net.lcc.dto.EventDto;
import net.lcc.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("all")
    public List<EventDto> getAllEventStages() {
        return null;
    }

    @GetMapping("{id}")
    public EventDto getAllEventStages(@PathVariable("id") Long id) {
        return null;
    }
}
