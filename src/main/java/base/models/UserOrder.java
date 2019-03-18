package base.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity
public class UserOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Min(1)
	private Long productAmount;
	private Long totalPrice;
	private OrderStatus orderStatus;

	public enum OrderStatus {
		ORDERED, PAYED, DELIVERED
	}

	protected UserOrder() {
	}

	public UserOrder(Product product, User user, Long productAmount, Long totalPrice, OrderStatus orderStatus) {
		this.product = product;
		this.user = user;
		this.productAmount = productAmount;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;

	}

	@Override
	public String toString() {
		return String.format(
				"UserOrder[id=%d, product='%s', user='%s', productAmount='%s', totalPrice='%s', orderStatus = '%s']",
				id, product, user, productAmount, totalPrice, orderStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Long productAmount) {
		this.productAmount = productAmount;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}