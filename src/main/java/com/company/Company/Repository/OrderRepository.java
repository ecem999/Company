package com.company.Company.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Company.Entity.Order;

public interface OrderRepository extends  JpaRepository<Order, Long> {

	public List<Order> findByCustomerId(Long id);
}
