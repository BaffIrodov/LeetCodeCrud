package net.lcc.readers;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.lcc.dto.EventStageDto;
import net.lcc.entities.QEvent;
import net.lcc.entities.QEventStage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventStageReader {
    private static final QEventStage eventStage = QEventStage.eventStage;
    private static final QEvent event = QEvent.event;
    private final JPAQueryFactory queryFactory;


    public static QBean<EventStageDto> getMappedSelectForEventStageDto() {
        return Projections.bean(
                EventStageDto.class,
                eventStage.id,
                eventStage.eventId,
                eventStage.name,
                eventStage.archive
        );
    }

    public List<EventStageDto> getAllEventStages(boolean showArchive) {
        return queryFactory.from(eventStage)
                .select(getMappedSelectForEventStageDto())
                .where(eventStage.archive.eq(showArchive))
                .fetch();
    }

    public List<EventStageDto> getEventStageByEventId(Long eventStageId) {
        return getEventStageByEventId(eventStageId, false);
    }

    public List<EventStageDto> getEventStageByEventId(Long eventId, Boolean showArchive) {
        return queryFactory.from(eventStage)
                .leftJoin(event).on(event.id.eq(eventStage.eventId))
                .select(getMappedSelectForEventStageDto())
                .where(event.id.eq(eventId)
                        .and(eventStage.archive.eq(showArchive)))
                .fetch();
    }
}
