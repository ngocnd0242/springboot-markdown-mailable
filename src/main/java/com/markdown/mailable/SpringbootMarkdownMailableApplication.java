package com.markdown.mailable;

import com.markdown.mailable.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringbootMarkdownMailableApplication implements ApplicationRunner {

	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMarkdownMailableApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("START... Sending mail");
		emailService.sendSimpleMessage("ngocnd08@gmail.com", "Demo", "demo");
		log.info("END... Email sent success");
	}
}
