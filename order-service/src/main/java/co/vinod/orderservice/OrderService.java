package co.vinod.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

	String url = "http://localhost:3003/api/lineitems/of/order/";
	@Autowired
	OrderDao dao;

	@Autowired
	RestTemplate template;

	public Iterable<Order> getOrdersForCustomer(String customerId) {
		Iterable<Order> orders = dao.findByCustomerId(customerId);

		orders.forEach(o -> {
			Object lineItem = template.exchange(url + o.getId(), HttpMethod.GET, null, Object.class);
			o.getLineItems().add(lineItem);
		});

		return orders;
	}
}
