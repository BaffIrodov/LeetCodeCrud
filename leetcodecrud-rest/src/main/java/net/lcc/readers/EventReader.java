package net.lcc.readers;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.lcc.dto.EventDto;
import net.lcc.entities.QEvent;
import net.lcc.entities.QEventStage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventReader {

    private static final QEvent event = QEvent.event;
    private static final QEventStage eventStage = QEventStage.eventStage;
    private final JPAQueryFactory queryFactory;


    public static QBean<EventDto> getMappedSelectForEventDto() {
        return Projections.bean(
                EventDto.class,
                event.id,
                event.name
        );
    }

    // todo доделать
    public List<EventDto> getEventsWithStages(Long positionId) {
        return queryFactory.from(event)
                .leftJoin(eventStage).on(eventStage.eventId.eq(event.id))
                .select(getMappedSelectForEventDto())
                .fetch();
    }

    public List<EventDto> getAllEvents() {
        return queryFactory.from(event)
                .select(getMappedSelectForEventDto())
                .fetch();
    }
}
