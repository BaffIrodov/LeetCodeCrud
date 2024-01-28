package net.lcc.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.lcc.dto.EventDto;
import net.lcc.entities.Event;
import net.lcc.readers.EventReader;
import net.lcc.repositories.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class EventService {

    private final EventReader eventReader;
    private final EventRepository eventRepository;

    public List<EventDto> getAllEvents(boolean showArchive) {
        return eventReader.getAllEvents(showArchive);
    }

    @GetMapping("{id}")
    public EventDto getEvent(@PathVariable("id") Long id) {
        return eventReader.getEventById(id);
    }

    @PostMapping("create")
    @Transactional
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        Event event = new Event(eventDto);
        event = this.eventRepository.save(event);
//        this.messageService.createMessagesForAllUsers(event.getId(),
//                "Новый заказ №" + event.getNumber());
//        this.messageService.createTelegramMessagesForAllUsers("Новый заказ №" + event.getNumber());
        return eventReader.getEventById(event.getId());
    }

    @PutMapping("{id}/update")
    @Transactional
    public EventDto updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        Event event = this.eventRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Дефолт-родитель не найден!");
                }
        );
        event.update(eventDto);
        return eventReader.getEventById(event.getId());
    }

    @DeleteMapping("{id}/archive")
    @Transactional
    public void archiveEvent(@PathVariable Long id) {
        Event event = this.eventRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Дефолт-родитель не найден!");
                }
        );
        event.archive();
    }

}