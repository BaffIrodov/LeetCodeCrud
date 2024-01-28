package net.lcc.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.lcc.dto.EventStageDto;
import net.lcc.dto.MessageDto;
import net.lcc.services.EventService;
import net.lcc.services.EventStageService;
import net.lcc.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event-stage")
@RequiredArgsConstructor
public class EventStageController {
    private final EventStageService eventStageService;

    @GetMapping("all")
    public List<EventStageDto> getAllEventStages() {
        return null;
    }

    @GetMapping("{id}")
    public EventStageDto getAllEventStages(@PathVariable("id") Long id) {
        return null;
    }
}
