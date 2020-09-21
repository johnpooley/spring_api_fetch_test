package com.example.demo2;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Demo2Application {

	private static final Logger log =
			LoggerFactory.getLogger(Demo2Application.class);

	public static void main(String[] args){
		SpringApplication.run(Demo2Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
	@Bean
	public CommandLineRunner run (RestTemplate restTemplate) throws Exception{
		return args ->{
			Quote quote = restTemplate.getForObject(
					"https://gturnquist-quoters.cfapps.io/api/random", Quote.class
			);
			log.info(quote.toString());
		};
	}

	@Bean
	public CommandLineRunner run2 (RestTemplate restTemplate) throws Exception{
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		return args ->{
			Pokemon pokemon = restTemplate.getForObject(
					"https://pokeapi.co/api/v2/pokemon/1/", Pokemon.class
			);
			log.info(pokemon.toString());
		};
	}

}
