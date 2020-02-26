package co.vinod.orderservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {
	@Id
	@Column(name = "order_id")
	private Integer id;
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "order_date")
	private Date orderDate;
	@Column(name = "required_date")
	private Date requiredDate;
	@Column(name = "shipped_date")
	private Date shippedDate;
	@Column(name = "ship_name")
	private String shipToName;
	@Column(name = "ship_address")
	private String shipToAddress;
	@Column(name = "ship_city")
	private String shipToCity;
	@Column(name = "ship_region")
	private String shipToRegion;
	@Column(name = "ship_country")
	private String shipToCountry;
	@Column(name = "ship_postal_code")
	private String shipToPostalCode;

	@Transient
	List<Object> lineItems = new ArrayList<Object>();
}
