package com.example.BankApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@ComponentScan({"com.example.BankApplication.appuser", "com.example.BankApplication.registration"})
//@EnableJpaRepositories("com.example.BankApplication.appuser"
//@EntityScan(basePackages = {"com.example.BankApplication"})
@EnableJpaRepositories(basePackages = "com.example.BankApplication")


//@ComponentScan({"com.example.BankApplication.registration"})
public class BankApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
<<<<<<< HEAD
	//TODO:napraw wysylanie maili
=======

//	@Bean
//	public JavaMailSender getJavaMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//
//		mailSender.setUsername("projektpapz28@gmail.com");
//		mailSender.setPassword("trudne@haslo");
//
//		Properties props = mailSender.getJavaMailProperties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.debug", "true");
//
//		return mailSender;
//	}
//	TODO: repair sending emails
>>>>>>> 0392dbe (email added, emails sending doesnt work yet)
}


//	@Bean
//	public JavaMailSender getJavaMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//
//		mailSender.setUsername("projektpapz28@gmail.com");
//		mailSender.setPassword("trudne@haslo");
//
//		Properties props = mailSender.getJavaMailProperties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.debug", "true");
//
//		return mailSender;
//	}


