package by.sacuta.exchangeService.dao;

import by.sacuta.exchangeService.model.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile,Long> {
   Profile findByUsername(String username);
}
