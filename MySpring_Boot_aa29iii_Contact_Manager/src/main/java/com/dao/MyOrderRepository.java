package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Long>{

	public MyOrder findByOrderId(String orderId);
	
}
