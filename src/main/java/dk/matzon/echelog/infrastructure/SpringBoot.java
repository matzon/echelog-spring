package dk.matzon.echelog.infrastructure;

import dk.matzon.echelog.application.Echelog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
public class SpringBoot {
  
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot.class, args);
 }
  
  @Bean
  public Echelog echelog() {
    return new Echelog();
  }
}