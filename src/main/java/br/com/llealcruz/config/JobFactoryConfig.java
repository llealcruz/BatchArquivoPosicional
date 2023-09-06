package br.com.llealcruz.config;

import br.com.llealcruz.job.PrimeiroJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobFactoryConfig {

    @Bean
    public PrimeiroJob primeiroJob() {
        return new PrimeiroJob();
    }
}
