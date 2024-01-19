package net.lcc.generators;

import jakarta.annotation.PostConstruct;
import net.lcc.entities.Event;
import net.lcc.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {

    @Autowired
    private EventRepository eventRepository;


    @Value("${generators.event}")
    private Boolean generatorEventEnable;

    private int eventCount = 15;

    @PostConstruct
    public void generateData() {
        generateEvents();
    }

    public void generateEvents() {
        if (generatorEventEnable) {
            for (int i = 0; i < eventCount; i++) {
                eventRepository.save(new Event("generatedName_" + (i+1)));
            }
        }
    }
}
