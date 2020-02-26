package co.vinod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import brave.sampler.Sampler;

@EnableJpaRepositories
@SpringBootApplication
public class CategoryServiceApplication {


	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	
	public static void main(String[] args) {

		SpringApplication.run(CategoryServiceApplication.class, args);
		
	}

}
