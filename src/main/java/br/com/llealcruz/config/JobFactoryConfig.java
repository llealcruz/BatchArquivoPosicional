package br.com.llealcruz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.llealcruz.job.PrimeiroJob;

@Configuration
public class JobFactoryConfig {

	@Bean
	public PrimeiroJob primeiroJob() {
		return new PrimeiroJob();
	}
}
