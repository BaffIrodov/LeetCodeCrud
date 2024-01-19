package net.lcc.readers;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.lcc.dto.EventStageDto;
import net.lcc.entities.QEventStage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventStageReader {
    private static final QEventStage eventStage = QEventStage.eventStage;
    private final JPAQueryFactory queryFactory;


    public static QBean<EventStageDto> getMappedSelectForEventStageDto() {
        return Projections.bean(
                EventStageDto.class,
                eventStage.id,
                eventStage.eventId,
                eventStage.name
        );
    }

    public List<EventStageDto> getAllEventStages() {
        return queryFactory.from(eventStage)
                .select(getMappedSelectForEventStageDto())
                .fetch();
    }
}
