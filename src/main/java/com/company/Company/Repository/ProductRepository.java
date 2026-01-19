package com.company.Company.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Company.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	
}
