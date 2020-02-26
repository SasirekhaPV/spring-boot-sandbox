package co.vinod.lineitemservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LineItemService {
	@Autowired
	LineItemDao dao;
	@Autowired
	RestTemplate template;

	String url = "http://localhost:3002/api/products/";

	public Iterable<LineItem> getLineItemsForOrder(Integer orderId) {
		Iterable<LineItem> lineItems = dao.findByOrderId(orderId);

		lineItems.forEach(li -> {
			Object product = template.exchange(url + li.getProductId(), HttpMethod.GET, null, Object.class);
			li.setProduct(product);
		});

		return lineItems;
	}

}
