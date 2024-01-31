package net.lcc.repositories;

import net.lcc.entities.DefaultChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultChildRepository extends JpaRepository<DefaultChild, Long> {
}
