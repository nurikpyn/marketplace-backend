package br.com.vfs.marketplacebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("/")
public class MarketplaceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceBackendApplication.class, args);
	}

}
