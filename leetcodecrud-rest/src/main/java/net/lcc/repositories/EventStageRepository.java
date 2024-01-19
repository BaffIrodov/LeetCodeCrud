package net.lcc.repositories;

import net.lcc.entities.EventStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStageRepository extends JpaRepository<EventStage, Long> {
}
