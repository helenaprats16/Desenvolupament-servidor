package org.alumne.helena.primer_app_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="org.alumne.helena.primer_app_spring_boot")
public class PrimerAppSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimerAppSpringBootApplication.class, args);
	}

}
