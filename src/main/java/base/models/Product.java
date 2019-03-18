package base.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String productName;
	private String productDescription;
	private Long pricePerUnit;
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<UserOrder> orders;

	protected Product() {
	}

	public Product(String productName, String productDescription, Long pricePerUnit) {
		this.productName = productName;
		this.productDescription = productDescription;
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public String toString() {
		return String.format("Product[id=%d, productName='%s', productDescription='%s', pricePerUnit='%s']", id,
				productName, productDescription, pricePerUnit);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Long getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Long pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
}