package co.vinod.lineitemservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemDao extends CrudRepository<LineItem, LineItemKey> {

	public Iterable<LineItem> findByOrderId(Integer orderId);
}
