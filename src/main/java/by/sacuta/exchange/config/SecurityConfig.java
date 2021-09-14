package by.sacuta.exchange.config;

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
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.LinkedList;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyCustomUserDetailsService myCustomUserDetailsService;

    public SecurityConfig(MyCustomUserDetailsService myCustomUserDetailsService) {
        this.myCustomUserDetailsService = myCustomUserDetailsService;
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
            ArrayList list=new ArrayList();
          return new Profile();
///dev 1;


        };
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(myCustomUserDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}