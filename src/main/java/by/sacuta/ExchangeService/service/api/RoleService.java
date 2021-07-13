package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface RoleService extends JpaRepository<Role,Long> {
}
