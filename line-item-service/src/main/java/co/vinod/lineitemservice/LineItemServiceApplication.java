package co.vinod.lineitemservice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaRepositories
@RestController
@SpringBootApplication
public class LineItemServiceApplication {

	@Autowired
	LineItemService service;

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(LineItemServiceApplication.class, args);
	}

	@GetMapping("/api/lineitems/of/order/{id}")
	public ResponseEntity<Map<String, Object>> get(@PathVariable Integer id) {
		log.info("got request for line items of order with id {}", id);
		Iterable<LineItem> lineItems = service.getLineItemsForOrder(id);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("lineItems", lineItems);
		log.info("returning line items of order with id {}", id);
		return ResponseEntity.ok(map);
	}

}
