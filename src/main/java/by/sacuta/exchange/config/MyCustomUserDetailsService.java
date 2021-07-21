package by.sacuta.exchange.config;

import by.sacuta.exchange.dao.ProfileDao;
import by.sacuta.exchange.dao.RoleDao;
import by.sacuta.exchange.domain.model.Profile;
import by.sacuta.exchange.domain.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Service
@Transactional
public class MyCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ProfileDao profileDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileDao.findByUsername(username);
        if (profile == null) {
            throw new UsernameNotFoundException("profile not found");
        }
        return profile;
    }

    public boolean saveUser(Profile profile) {
        boolean b = true;
        for (int i = 0; i < profileDao.findAll().size(); i++) {
            Profile profile1 = profileDao.findAll().get(i);
            if (profile1.getUsername().equals(profile.getUsername())) {
                b = false;
            }
        }
        if (b) {
            List<Role> roles = new LinkedList<>();
            roles.add(roleDao.getById(1L));
            profile.setRoles(roles);
            profile.setPassword((profile.getPassword()));
            profileDao.save(profile);
        }
        return true;
    }
}