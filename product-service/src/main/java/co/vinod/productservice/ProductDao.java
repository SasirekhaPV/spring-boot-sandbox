package co.vinod.productservice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {

	// convention
	public Iterable<Product> findByDiscontinued(Boolean isDiscontinued);
	
	// non-conventional
	@Query("from Product where unitPrice between ?1 and ?2")
	public Iterable<Product>  productsByPrice(Double min, Double max);
}
