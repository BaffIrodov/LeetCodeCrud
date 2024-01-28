package net.lcc.generators;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.lcc.entities.Event;
import net.lcc.entities.EventStage;
import net.lcc.repositories.EventRepository;
import net.lcc.repositories.EventStageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataGenerator {

    private final EventRepository eventRepository;
    private final EventStageRepository eventStageRepository;

    @Value("${generators.event}")
    private Boolean generatorEventEnable;

    @Value("${generators.event-stage}")
    private Boolean generatorEventStageEnable;

    private int eventCount = 15;
    private int eventStageCount = 5;

    @PostConstruct
    public void generateData() {
        generateEvents();
        generateEventStages();
    }

    public void generateEvents() {
        if (generatorEventEnable) {
            for (int i = 0; i < eventCount; i++) {
                eventRepository.save(new Event("generatedName_" + (i + 1)));
            }
        }
    }

    public void generateEventStages() {
        if (generatorEventStageEnable) {
            List<Event> events = eventRepository.findAll();
            for (Event event : events) {
                List<EventStage> eventStagesForSave = new ArrayList<>();
                for (int i = 0; i < eventStageCount; i++) {
                    EventStage eventStage = new EventStage(event.getId(), "generatedName_" + (i + 1));
                    eventStagesForSave.add(eventStage);
                }
                eventStageRepository.saveAll(eventStagesForSave);
            }
        }
    }
}
