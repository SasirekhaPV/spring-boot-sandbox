package co.vinod.productservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product {
	@Id
	@Column(name = "product_id")
	private Integer id;
	@Column(name = "product_name")
	private String name;
	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "supplier_id")
	private Integer supplierId;
	@Column(name = "unit_price")
	private Double unitPrice;
	@Column(name = "units_in_stock")
	private Integer unitsInStock;
	@Column(name = "units_on_order")
	private Integer unitsOnOrder;
	@Column(name = "reorder_level")
	private Integer reorderLevel;
	private Boolean discontinued;
}
