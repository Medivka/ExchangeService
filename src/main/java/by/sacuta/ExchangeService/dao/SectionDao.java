package by.sacuta.ExchangeService.dao;

import by.sacuta.ExchangeService.model.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionDao extends JpaRepository<Section,Long> {
}
