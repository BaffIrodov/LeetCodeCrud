package net.lcc.repositories;

import net.lcc.entities.DefaultParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultParentRepository extends JpaRepository<DefaultParent, Long> {
}
