package base.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import base.models.UserOrder;

public interface UserOrderRepository extends CrudRepository<UserOrder, Long> {
	List<UserOrder> findByUserId(Long userId);

	List<UserOrder> findByProductId(Long productId);

}