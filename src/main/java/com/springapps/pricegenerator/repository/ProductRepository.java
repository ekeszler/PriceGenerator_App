package com.springapps.pricegenerator.repository;

import com.springapps.pricegenerator.model.Product;
import com.springapps.pricegenerator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
