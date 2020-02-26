package co.vinod.customerservice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories
@RestController
@SpringBootApplication
public class CustomerServiceApplication {

	@Autowired
	CustomerDao dao;
	
	@Autowired
	RestTemplate template;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@GetMapping("/api/customers/{id}")
	public ResponseEntity<Map<String, Object>> get(@PathVariable String id) {
		Customer customer = dao.findById(id).get();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("customer", customer);
		
		String url = "http://localhost:3004/api/orders/of/customer/" + id;
		Object orders = template.exchange(url, HttpMethod.GET, null, Object.class);
		map.put("orders", orders);
		
		return ResponseEntity.ok(map);
	}

}
