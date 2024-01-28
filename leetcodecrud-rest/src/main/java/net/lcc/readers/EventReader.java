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

    public EventDto getEventById(Long eventId) {
        return queryFactory.from(event)
                .select(getMappedSelectForEventDto())
                .where(event.id.eq(eventId))
                .fetchFirst();
    }

    // todo доделать
    public EventDto getEventWithStages(Long positionId) {
        return queryFactory.from(event)
                .leftJoin(eventStage).on(eventStage.eventId.eq(event.id))
                .select(getMappedSelectForEventDto())
                .where(event.id.eq(positionId))
                .fetchOne();
    }

    public List<EventDto> getAllEvents(boolean showArchive) {
        return queryFactory.from(event)
                .select(getMappedSelectForEventDto())
                .where(event.archive.eq(showArchive))
                .fetch();
    }
}
