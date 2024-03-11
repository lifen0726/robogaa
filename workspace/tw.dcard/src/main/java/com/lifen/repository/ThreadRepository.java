package com.lifen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lifen.model.Thread;

public interface ThreadRepository extends JpaRepository<Thread, Integer> {
	
	List<Thread> findByCategoryid(int categoryid);
	
	List<Thread> findByMemberid(int memberid);
}

