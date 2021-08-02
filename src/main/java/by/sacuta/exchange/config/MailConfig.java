package by.sacuta.exchange.config;


import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

   private static String MAIN_POST= "exchangetestexample@gmail.com";
   private static String MAIN_PASSWORD= "123456service";

    /**
     * * link
     * https://myaccount.google.com/lesssecureapps?pli=1&rapt=AEjHL4MIMppDZn8IYzpSQ1hvFMlOzo8ZtMCq1-gYz463_ZfH_OwQ7fEgWrmYhGsM9prTtiyrBnOcpaPkU2o1FSxNtJF9DJyfWg
     */

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(MAIN_POST);
        mailSender.setPassword(MAIN_PASSWORD);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
}