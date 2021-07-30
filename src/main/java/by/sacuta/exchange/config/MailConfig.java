package by.sacuta.exchange.config;


import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    /**
     * https://myaccount.google.com/lesssecureapps?pli=1&rapt=AEjHL4MIMppDZn8IYzpSQ1hvFMlOzo8ZtMCq1-gYz463_ZfH_OwQ7fEgWrmYhGsM9prTtiyrBnOcpaPkU2o1FSxNtJF9DJyfWg
     * link
     */

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("exchangetestexample@gmail.com");
        mailSender.setPassword("123456service");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

}