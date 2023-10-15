package com.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

	
//pagination	
	
//	@Query("from Contact as c where c.userr.uid = :uid")
//	public List<Contact> getContactsByUid(@Param("uid")int uid);

	@Query("from Contact as c where c.userr.uid = :uid") 
	public Page<Contact> getContactsByUid(@Param("uid")int uid,Pageable pageable);
	// Pageable object contains
	// two values
	//currentPage - page
	//contact per page - 3
	
	
	@Query("from Contact as c where c.userr.uid = :uid and c.cname like %:keyword%")
	public List<Contact> findByUidAndKeyword(@Param("uid")int uid,@Param("keyword")String keyword);
	
	
}
