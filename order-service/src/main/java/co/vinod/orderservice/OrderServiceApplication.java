package co.vinod.orderservice;

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
public class OrderServiceApplication {

	@Autowired
	OrderService service;
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@GetMapping("/api/orders/of/customer/{id}")
	public ResponseEntity<Map<String, Object>> get(@PathVariable String id) {
		log.info("got a request for orders of customer with id {}", id);
		Iterable<Order> orders = service.getOrdersForCustomer(id);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("orders", orders);
		log.info("returning orders for customer with id {}", id);
		return ResponseEntity.ok(map);
	}
}
