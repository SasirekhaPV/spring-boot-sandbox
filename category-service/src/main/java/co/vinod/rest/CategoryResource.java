package co.vinod.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.vinod.dao.CategoryDao;
import co.vinod.entity.Category;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

	@Autowired
	CategoryDao dao;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAll() {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("data", dao.findAll());
			return ResponseEntity.status(201).body(map);
		} catch (Exception e) {
			map.put("error", e.getMessage());
			return ResponseEntity.status(550).body(map);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Integer id) {
		log.info("got request for category with id {}", id);
		return ResponseEntity.ok(dao.findById(id).get());
	}

	@PostMapping
	public ResponseEntity<Category> addNew(@RequestBody Category category) {
		category.setId(null); // to ensure generation of new primary key value
		category = dao.save(category);
		return ResponseEntity.ok(category);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
		category.setId(id); // ignore the id present in the category
		category = dao.save(category);
		return ResponseEntity.ok(category);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		try {
			Category c = dao.findById(id).get();
			dao.deleteById(id);
			return ResponseEntity.ok(c);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category found for id::" + id);
		}
	}

}
