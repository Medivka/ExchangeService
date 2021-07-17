package by.sacuta.ExchangeService.dao;

import by.sacuta.ExchangeService.model.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Long> {
}
