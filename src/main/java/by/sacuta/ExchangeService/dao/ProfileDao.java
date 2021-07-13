package by.sacuta.ExchangeService.dao;

import by.sacuta.ExchangeService.model.model.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile,Long> {
   Profile findByUsername(String username);
}
