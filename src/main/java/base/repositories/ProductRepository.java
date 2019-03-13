package base.repositories;

import org.springframework.data.repository.CrudRepository;

import base.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Product findByProductName(String productName);

}