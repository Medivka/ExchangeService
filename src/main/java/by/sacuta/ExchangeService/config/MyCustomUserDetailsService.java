package by.sacuta.ExchangeService.config;

import by.sacuta.ExchangeService.dao.ProfileDao;
import by.sacuta.ExchangeService.dao.RoleDao;
import by.sacuta.ExchangeService.model.model.Profile;
import by.sacuta.ExchangeService.model.model.Role;

import by.sacuta.ExchangeService.service.api.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private ProfileService profileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     UserDetails loadedUser;
        try {
            Profile profile= profileService.findByUsername(username);
            loadedUser =new User(
                       profile.getUsername()
                    , profile.getPassword()
                    , profile.getRoles())
            ;
            System.out.println(username);
        } catch (Exception repositoryProblem) {
             throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        return loadedUser;
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
            List<Role> roles=new LinkedList<>();
            roles.add(roleDao.getById(1l));
            profile.setRoles(roles);
            profile.setPassword((profile.getPassword()));
            profileDao.save(profile);
        }
        return true;
    }
}