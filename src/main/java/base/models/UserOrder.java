package base.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long productId;
	private Long userId;
	private Long productAmount;
	private Long totalPrice;

	protected UserOrder() {
	}

	public UserOrder(Long productId, Long userId, Long productAmount, Long totalPrice) {
		this.productId = productId;
		this.userId = userId;
		this.productAmount = productAmount;
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return String.format("UserOrder[id=%d, productId='%s', userId='%s', productAmount='%s', totalPrice='%s']", id,
				productId, userId, productAmount, totalPrice);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
}