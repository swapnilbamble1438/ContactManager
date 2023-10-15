package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Userr;




public interface UserrRepository extends JpaRepository<Userr, Integer> {

	@Query("select u from Userr u where u.uemail = :uemail")
	public Userr getUserrByUserrName(@Param("uemail")String uemail);
	
	
}

