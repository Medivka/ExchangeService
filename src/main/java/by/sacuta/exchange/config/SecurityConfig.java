package by.sacuta.exchange.config;

import by.sacuta.exchange.domain.enums.ProfileStatus;
import by.sacuta.exchange.domain.model.Profile;
import by.sacuta.exchange.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyCustomUserDetailsService myCustomUserDetailsService;
private final ProfileService profileService;
    public SecurityConfig(MyCustomUserDetailsService myCustomUserDetailsService, ProfileService profileService) {
        this.myCustomUserDetailsService = myCustomUserDetailsService;
        this.profileService=profileService;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .permitAll()
//                .formLogin()
//                .disable()
        ;
    }

    @Bean
    public PrincipalExtractor principalExtractor(MyCustomUserDetailsService myCustomUserDetailsService){
        return map -> {
            if(profileService.existsByUsername((String) map.get("email"))){
               return myCustomUserDetailsService.loadUserByUsername((String) map.get("email"));
            }else {
                Profile profile=new Profile();
                profile.setUsername((String) map.get("email"));
                profile.setPassword((String) map.get("email"));
                profile.setName((String) map.get("given_name"));
                profile.setEmail((String) map.get("email"));
                profile.setLastname("fromGoogle");
                profile.setAge(30);
                profile.setCity("SiliconValley");
                profile.setStatus(ProfileStatus.LISTENER);

                return myCustomUserDetailsService.saveUser(profile);
            }

///dev 1;


        };
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(myCustomUserDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}