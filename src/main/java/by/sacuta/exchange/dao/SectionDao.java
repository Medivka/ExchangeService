package by.sacuta.exchange.dao;

import by.sacuta.exchange.domain.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionDao extends JpaRepository<Section,Long> {
}
