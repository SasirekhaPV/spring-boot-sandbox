package co.vinod.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductResource {
	
	@Autowired
	ProductDao dao;
	
	// --> http://localhost:3001/api/products
	// --> http://localhost:3001/api/products?_page=2
	// --> http://localhost:3001/api/products?_limit=20
	// --> http://localhost:3001/api/products?_page=2&_limit=20
	@GetMapping
	public ResponseEntity<Iterable<Product>> getAll(
			@RequestParam(defaultValue = "0") Integer _page, 
			@RequestParam(defaultValue = "10") Integer _limit) {

		Page<Product> model = dao.findAll(PageRequest.of(_page, _limit));
		return ResponseEntity.ok(model);
	}

	// --> http://localhost:3001/api/products/between/price/50/and/500
	@GetMapping("/between/price/{min}/and/{max}")
	public ResponseEntity<Iterable<Product>> getByPrice(
			@PathVariable Double min, 
			@PathVariable Double max) {
		return ResponseEntity.ok(dao.productsByPrice(min, max));
	}
	
}





