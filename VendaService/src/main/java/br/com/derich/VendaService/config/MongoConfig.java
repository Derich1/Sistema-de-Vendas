package br.com.derich.VendaService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.derich.VendaService.repository")
public class MongoConfig {

}
