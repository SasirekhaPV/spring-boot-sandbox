package co.vinod.orderservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends CrudRepository<Order, Integer> {

	public Iterable<Order> findByCustomerId(String customerId);
}
