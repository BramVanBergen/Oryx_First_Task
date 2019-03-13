package base.repositories;

import org.springframework.data.repository.CrudRepository;

import base.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

}