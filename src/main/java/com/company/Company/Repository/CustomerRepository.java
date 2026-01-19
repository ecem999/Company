package com.company.Company.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Company.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
