package fr.imie.productmanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement
@XmlType(propOrder={ "id", "name", "description", "price", "categoryId" })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private float price;
	
	@ManyToOne
	@JoinColumn(name = "category_fk")
	private Category category;
	
	public Product() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * Hack to display the category ID.
	 * @return the ID of the {@link Product#getCategory() category}.
	 */
	@XmlElement(name = "category")
	public Long getCategoryId() {
		Long id;
		
		if (this.getCategory() != null) {
			id = this.getCategory().getId();
		} else {
			id = null;
		}
		
		return id;
	}

	/**
	 * Mark as transient to prevent endless loop.
	 * @return
	 */
	@XmlTransient
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}

}
