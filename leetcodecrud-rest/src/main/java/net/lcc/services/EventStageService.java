package net.lcc.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.lcc.dto.EventStageDto;
import net.lcc.entities.EventStage;
import net.lcc.readers.EventStageReader;
import net.lcc.repositories.EventStageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class EventStageService {

    private final EventStageReader eventStageReader;
    private final EventStageRepository eventStageRepository;

    @GetMapping("all")
    public List<EventStageDto> getAllEventStages(boolean showArchive) {
        return eventStageReader.getAllEventStages(showArchive);
    }

    @GetMapping("{id}")
    public List<EventStageDto> getEventStagesByEventId(@PathVariable("id") Long eventId, boolean showArchive) {
        return eventStageReader.getEventStageByEventId(eventId, showArchive);
    }

    @PostMapping("create")
    @Transactional
    public List<EventStageDto> createEventStage(@RequestBody EventStageDto eventStageDto) {
        EventStage eventStage = new EventStage(eventStageDto);
        eventStage = this.eventStageRepository.save(eventStage);
//        this.messageService.createMessagesForAllUsers(event.getId(),
//                "Новый заказ №" + event.getNumber());
        return eventStageReader.getEventStageByEventId(eventStage.getId());
    }

    @PutMapping("{id}/update")
    @Transactional
    public List<EventStageDto> updateEventStage(@PathVariable Long id, @RequestBody EventStageDto eventStageDto) {
        EventStage eventStage = this.eventStageRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Дефолт-ребенок не найден!");
                }
        );
        eventStage.update(eventStageDto);
        return eventStageReader.getEventStageByEventId(eventStage.getId());
    }

    @DeleteMapping("{id}/archive")
    @Transactional
    public void archiveEventStage(@PathVariable Long id) {
        EventStage eventStage = this.eventStageRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Дефолт-ребенок не найден!");
                }
        );
        eventStage.archive();
    }

}