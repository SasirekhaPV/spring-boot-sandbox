package co.vinod.lineitemservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@IdClass(LineItemKey.class)
@NoArgsConstructor
@Getter
@Setter
public class LineItem {

	@Id
	@Column(name = "order_id")
	private Integer orderId;
	@Id
	@Column(name = "product_id")
	private Integer productId;
	private Integer quantity;
	@Column(name = "unit_price")
	private Double unitPrice;
	private Double discount;
	
	@Transient
	Object product;
}
