package com.quicktodo;

import com.quicktodo.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
public class QuickToDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickToDoApplication.class, args);
	}

}
