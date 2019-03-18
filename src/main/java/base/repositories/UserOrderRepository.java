package base.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import base.models.User;
import base.models.UserOrder;

public interface UserOrderRepository extends CrudRepository<UserOrder, Long> {
	Iterable<UserOrder> findAllByUserId(Long userId);

	List<UserOrder> findByUser(User user);

	Iterable<UserOrder> findAllByProductId(Long productId);

}