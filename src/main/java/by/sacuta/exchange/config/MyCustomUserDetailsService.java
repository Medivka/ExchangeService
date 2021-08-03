package by.sacuta.exchange.config;

import by.sacuta.exchange.dao.RoleDao;
import by.sacuta.exchange.domain.model.Profile;
import by.sacuta.exchange.domain.model.Role;
import by.sacuta.exchange.service.impl.ProfileServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
public class MyCustomUserDetailsService implements UserDetailsService {

    private final RoleDao roleDao;
    private final ProfileServiceImpl profileService;

    public MyCustomUserDetailsService(RoleDao roleDao, ProfileServiceImpl profileService) {
        this.roleDao = roleDao;
        this.profileService = profileService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileService.findByUsername(username);
        if (profile == null) {
            throw new UsernameNotFoundException("profile not found");
        }
        return profile;
    }

    public boolean saveUser(Profile profile) {
        if (!profileService.existsByUsername(profile.getUsername())) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleDao.getById(1L));
            profile.setRoles(roles);
            profileService.save(profile);
            return true;
        }
        return false;
    }
}