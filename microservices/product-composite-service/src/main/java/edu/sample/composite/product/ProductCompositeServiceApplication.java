package edu.sample.composite.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(scanBasePackages = "edu.sample")
public class ProductCompositeServiceApplication {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
    public static void main(String[] args) {

        SpringApplication.run(ProductCompositeServiceApplication.class, args);
    }

}
