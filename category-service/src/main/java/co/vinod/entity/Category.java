package co.vinod.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
	@Id
	@Column(name = "category_id")
	@GeneratedValue(generator = "increment")
	private Integer id;
	@Column(name = "category_name")
	private String name;
	private String description;
}
