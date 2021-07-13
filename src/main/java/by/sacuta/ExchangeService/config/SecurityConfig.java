package by.sacuta.ExchangeService.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
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
                .permitAll()
//                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .formLogin()
                .disable()
                  ;
    }



    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(myCustomUserDetailsService);
    }
}