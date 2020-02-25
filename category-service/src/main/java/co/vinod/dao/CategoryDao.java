package co.vinod.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.vinod.entity.Category;

@Repository
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
