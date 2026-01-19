package com.company.Company.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Company.Entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	public List<Enrollment> findByOrderId(Long orderId);

	void deleteByOrderIdAndProductId(Long orderId, Long productId);
}
